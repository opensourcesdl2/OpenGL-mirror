
/*
Copyright (C) 2021 M.2.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
public final class AsyncProcessor<T> extends FlowableProcessor<T> {

    @SuppressWarnings("rawtypes")
    static final AsyncSubscription[] EMPTY = new AsyncSubscription[0];

    @SuppressWarnings("rawtypes")
    static final AsyncSubscription[] TERMINATED = new AsyncSubscription[0];

    final AtomicReference<AsyncSubscription<T>[]> subscribers;

    /** Write before updating subscribers, read after reading subscribers as TERMINATED. */
    Throwable error;

    /** Write before updating subscribers, read after reading subscribers as TERMINATED. */
    T value;

    /**
     * Creates a new AsyncProcessor.
     * @param <T> the value type to be received and emitted
     * @return the new AsyncProcessor instance
     */
    @CheckReturnValue
    @NonNull
    public static <T> AsyncProcessor<T> create() {
        return new AsyncProcessor<>();
    }

    /**
     * Constructs an AsyncProcessor.
     * @since 2.0
     */
    @SuppressWarnings("unchecked")
    AsyncProcessor() {
        this.subscribers = new AtomicReference<>(EMPTY);
    }

    @Override
    public void onSubscribe(@NonNull Subscription s) {
        if (subscribers.get() == TERMINATED) {
            s.cancel();
            return;
        }
        // AsyncProcessor doesn't bother with request coordination.
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(@NonNull T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (subscribers.get() == TERMINATED) {
            return;
        }
        value = t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onError(@NonNull Throwable t) {
        ExceptionHelper.nullCheck(t, "onError called with a null Throwable.");
        if (subscribers.get() == TERMINATED) {
            RxJavaPlugins.onError(t);
            return;
        }
        value = null;
        error = t;
        for (AsyncSubscription<T> as : subscribers.getAndSet(TERMINATED)) {
            as.onError(t);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onComplete() {
        if (subscribers.get() == TERMINATED) {
            return;
        }
        T v = value;
        AsyncSubscription<T>[] array = subscribers.getAndSet(TERMINATED);
        if (v == null) {
            for (AsyncSubscription<T> as : array) {
                as.onComplete();
            }
        } else {
            for (AsyncSubscription<T> as : array) {
                as.complete(v);
            }
        }
    }

    @Override
    @CheckReturnValue
    public boolean hasSubscribers() {
        return subscribers.get().length != 0;
    }

    @Override
    @CheckReturnValue
    public boolean hasThrowable() {
        return subscribers.get() == TERMINATED && error != null;
    }

    @Override
    @CheckReturnValue
    public boolean hasComplete() {
        return subscribers.get() == TERMINATED && error == null;
    }

    @Override
    @Nullable
    @CheckReturnValue
    public Throwable getThrowable() {
        return subscribers.get() == TERMINATED ? error : null;
    }

    @Override
    protected void subscribeActual(@NonNull Subscriber<@NonNull ? super T> s) {
        AsyncSubscription<T> as = new AsyncSubscription<>(s, this);
        s.onSubscribe(as);
        if (add(as)) {
            if (as.isCancelled()) {
                remove(as);
            }
        } else {
            Throwable ex = error;
            if (ex != null) {
                s.onError(ex);
            } else {
                T v = value;
                if (v != null) {
                    as.complete(v);
                } else {
                    as.onComplete();
                }
            }
        }
    }

    /**
     * Tries to add the given subscriber to the subscribers array atomically
     * or returns false if the processor has terminated.
     * @param ps the subscriber to add
     * @return true if successful, false if the processor has terminated
     */
    boolean add(AsyncSubscription<T> ps) {
        for (;;) {
            AsyncSubscription<T>[] a = subscribers.get();
            if (a == TERMINATED) {
                return false;
            }

            int n = a.length;
            @SuppressWarnings("unchecked")
            AsyncSubscription<T>[] b = new AsyncSubscription[n + 1];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = ps;

            if (subscribers.compareAndSet(a, b)) {
                return true;
            }
        }
    }

    /**
     * Atomically removes the given subscriber if it is subscribed to this processor.
     * @param ps the subscriber's subscription wrapper to remove
     */
    @SuppressWarnings("unchecked")
    void remove(AsyncSubscription<T> ps) {
        for (;;) {
            AsyncSubscription<T>[] a = subscribers.get();
            int n = a.length;
            if (n == 0) {
                return;
            }

            int j = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] == ps) {
                    j = i;
                    break;
                }
            }

            if (j < 0) {
                return;
            }

            AsyncSubscription<T>[] b;

            if (n == 1) {
                b = EMPTY;
            } else {
                b = new AsyncSubscription[n - 1];
                System.arraycopy(a, 0, b, 0, j);
                System.arraycopy(a, j + 1, b, j, n - j - 1);
            }
            if (subscribers.compareAndSet(a, b)) {
                return;
            }
        }
    }

    /**
     * Returns true if this processor has any value.
     * <p>The method is thread-safe.
     * @return true if this processor has any value
     */
    @CheckReturnValue
    public boolean hasValue() {
        return subscribers.get() == TERMINATED && value != null;
    }

    /**
     * Returns a single value this processor currently has or null if no such value exists.
     * <p>The method is thread-safe.
     * @return a single value this processor currently has or null if no such value exists
     */
    @Nullable
    @CheckReturnValue
    public T getValue() {
        return subscribers.get() == TERMINATED ? value : null;
    }

    static final class AsyncSubscription<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = 5629876084736248016L;

        final AsyncProcessor<T> parent;

        AsyncSubscription(Subscriber<@NonNull ? super T> actual, AsyncProcessor<T> parent) {
            super(actual);
            this.parent = parent;
        }

        @Override
        public void cancel() {
            if (super.tryCancel()) {
                parent.remove(this);
            }
        }

        void onComplete() {
            if (!isCancelled()) {
                downstream.onComplete();
            }
        }

        void onError(Throwable t) {
            if (isCancelled()) {
                RxJavaPlugins.onError(t);
            } else {
                downstream.onError(t);
            }
        }
    }
}