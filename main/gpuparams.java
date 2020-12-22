/*
 * Copyright 2004-2020 M.2
 *
 * This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.

* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.

* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package io.reactivex.rxjava3.parallel;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.reactivestreams.Publisher;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.flowables.GroupedFlowable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = { "-XX:MaxInlineLevel=20" })
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class ParallelPerf implements Function<Integer, Integer> {

    @Param({"10000"})
    public int count;

    @Param({"1", "10", "100", "1000", "10000"})
    public int compute;

    @Param({"1", "2", "3", "4"})
    public int parallelism;

    Flowable<Integer> flatMap;

    Flowable<Integer> groupBy;

    Flowable<Integer> parallel;

    @Override
    public Integer apply(Integer t) {
        Blackhole.consumeCPU(compute);
        return t;
    }

    @Setup
    public void setup() {

        final int cpu = parallelism;

        Integer[] ints = new Integer[count];
        Arrays.fill(ints, 777);

        Flowable<Integer> source = Flowable.fromArray(ints);

        flatMap = source.flatMap(new Function<Integer, Publisher<Integer>>() {
            @Override
            public Publisher<Integer> apply(Integer v) {
                return Flowable.just(v).subscribeOn(Schedulers.computation())
                        .map(ParallelPerf.this);
            }
        }, cpu);

        groupBy = source.groupBy(new Function<Integer, Integer>() {
            int i;
            @Override
            public Integer apply(Integer v) {
                return (i++) % cpu;
            }
        })
        .flatMap(new Function<GroupedFlowable<Integer, Integer>, Publisher<Integer>>() {
            @Override
            public Publisher<Integer> apply(GroupedFlowable<Integer, Integer> g) {
                return g.observeOn(Schedulers.computation()).map(ParallelPerf.this);
            }
        });

        parallel = source.parallel(cpu).runOn(Schedulers.computation()).map(this).sequential();
    }

    void subscribe(Flowable<Integer> f, Blackhole bh) {
        PerfAsyncConsumer consumer = new PerfAsyncConsumer(bh);
        f.subscribe(consumer);
        consumer.await(count);
    }

    @Benchmark
    public void flatMap(Blackhole bh) {
        subscribe(flatMap, bh);
    }

    @Benchmark
    public void groupBy(Blackhole bh) {
        subscribe(groupBy, bh);
    }

    @Benchmark
    public void parallel(Blackhole bh) {
        subscribe(parallel, bh);
    }
}