

public class CanalClientException {
    import retrofit2.Retrofit;
    import com.google.common.reflect.TypeToken;
    import com.microsoft.azure.AzureServiceFuture;
    import com.microsoft.azure.CloudException;
    import com.microsoft.azure.ListOperationCallback;
    import com.microsoft.azure.management.azurestack.v2017_06_01.DeviceConfiguration;
    import com.microsoft.azure.management.azurestack.v2017_06_01.ErrorResponseException;
    import com.microsoft.azure.management.azurestack.v2017_06_01.MarketplaceProductLogUpdate;
    import com.microsoft.azure.Page;
    import com.microsoft.azure.PagedList;
    import com.microsoft.rest.ServiceCallback;
    import com.microsoft.rest.ServiceFuture;
    import com.microsoft.rest.ServiceResponse;
    import java.io.IOException;
    import java.util.List;
    import okhttp3.ResponseBody;
    import retrofit2.http.Body;
    import retrofit2.http.GET;
    import retrofit2.http.Header;
    import retrofit2.http.Headers;
    import retrofit2.http.Path;
    import retrofit2.http.POST;
    import retrofit2.http.Query;
    import retrofit2.http.Url;
    import retrofit2.Response;
    import rx.functions.Func1;
    import rx.Observable;
    
    /**
     * An instance of this class provides access to all the operations defined
     * in Products.
     */
    public class ProductsInner {
        /** The Retrofit service to perform REST calls. */
        private ProductsService service;
        /** The service client containing this operation class. */
        private AzureStackManagementClientImpl client;
    
        /**
         * Initializes an instance of ProductsInner.
         *
         * @param retrofit the Retrofit instance built from a Retrofit Builder.
         * @param client the instance of the service client containing this operation class.
         */
        public ProductsInner(Retrofit retrofit, AzureStackManagementClientImpl client) {
            this.service = retrofit.create(ProductsService.class);
            this.client = client;
        }
    
        /**
         * The interface defining all the services for Products to be
         * used by Retrofit to perform actually REST calls.
         */
        interface ProductsService {
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products list" })
            @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroup}/providers/Microsoft.AzureStack/registrations/{registrationName}/products")
            Observable<Response<ResponseBody>> list(@Path("subscriptionId") String subscriptionId, @Path("resourceGroup") String resourceGroup, @Path("registrationName") String registrationName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);
    
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products get" })
            @GET("subscriptions/{subscriptionId}/resourceGroups/{resourceGroup}/providers/Microsoft.AzureStack/registrations/{registrationName}/products/{productName}")
            Observable<Response<ResponseBody>> get(@Path("subscriptionId") String subscriptionId, @Path("resourceGroup") String resourceGroup, @Path("registrationName") String registrationName, @Path("productName") String productName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);
    
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products listDetails" })
            @POST("subscriptions/{subscriptionId}/resourceGroups/{resourceGroup}/providers/Microsoft.AzureStack/registrations/{registrationName}/products/{productName}/listDetails")
            Observable<Response<ResponseBody>> listDetails(@Path("subscriptionId") String subscriptionId, @Path("resourceGroup") String resourceGroup, @Path("registrationName") String registrationName, @Path("productName") String productName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);
    
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products getProducts" })
            @POST("subscriptions/{subscriptionId}/resourceGroups/{resourceGroup}/providers/Microsoft.AzureStack/registrations/{registrationName}/products/_all/GetProducts")
            Observable<Response<ResponseBody>> getProducts(@Path("subscriptionId") String subscriptionId, @Path("resourceGroup") String resourceGroup, @Path("registrationName") String registrationName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Body DeviceConfiguration deviceConfiguration, @Header("User-Agent") String userAgent);
    
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products getProduct" })
            @POST("subscriptions/{subscriptionId}/resourceGroups/{resourceGroup}/providers/Microsoft.AzureStack/registrations/{registrationName}/products/{productName}/GetProduct")
            Observable<Response<ResponseBody>> getProduct(@Path("subscriptionId") String subscriptionId, @Path("resourceGroup") String resourceGroup, @Path("registrationName") String registrationName, @Path("productName") String productName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Body DeviceConfiguration deviceConfiguration, @Header("User-Agent") String userAgent);
    
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products uploadLog" })
            @POST("subscriptions/{subscriptionId}/resourceGroups/{resourceGroup}/providers/Microsoft.AzureStack/registrations/{registrationName}/products/{productName}/uploadProductLog")
            Observable<Response<ResponseBody>> uploadLog(@Path("subscriptionId") String subscriptionId, @Path("resourceGroup") String resourceGroup, @Path("registrationName") String registrationName, @Path("productName") String productName, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Body MarketplaceProductLogUpdate marketplaceProductLogUpdate, @Header("User-Agent") String userAgent);
    
            @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.azurestack.v2017_06_01.Products listNext" })
            @GET
            Observable<Response<ResponseBody>> listNext(@Url String nextUrl, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);
    
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws ErrorResponseException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the PagedList&lt;ProductInner&gt; object if successful.
         */
        public PagedList<ProductInner> list(final String resourceGroup, final String registrationName) {
            ServiceResponse<Page<ProductInner>> response = listSinglePageAsync(resourceGroup, registrationName).toBlocking().single();
            return new PagedList<ProductInner>(response.body()) {
                @Override
                public Page<ProductInner> nextPage(String nextPageLink) {
                    return listNextSinglePageAsync(nextPageLink).toBlocking().single().body();
                }
            };
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<List<ProductInner>> listAsync(final String resourceGroup, final String registrationName, final ListOperationCallback<ProductInner> serviceCallback) {
            return AzureServiceFuture.fromPageResponse(
                listSinglePageAsync(resourceGroup, registrationName),
                new Func1<String, Observable<ServiceResponse<Page<ProductInner>>>>() {
                    @Override
                    public Observable<ServiceResponse<Page<ProductInner>>> call(String nextPageLink) {
                        return listNextSinglePageAsync(nextPageLink);
                    }
                },
                serviceCallback);
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the PagedList&lt;ProductInner&gt; object
         */
        public Observable<Page<ProductInner>> listAsync(final String resourceGroup, final String registrationName) {
            return listWithServiceResponseAsync(resourceGroup, registrationName)
                .map(new Func1<ServiceResponse<Page<ProductInner>>, Page<ProductInner>>() {
                    @Override
                    public Page<ProductInner> call(ServiceResponse<Page<ProductInner>> response) {
                        return response.body();
                    }
                });
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the PagedList&lt;ProductInner&gt; object
         */
        public Observable<ServiceResponse<Page<ProductInner>>> listWithServiceResponseAsync(final String resourceGroup, final String registrationName) {
            return listSinglePageAsync(resourceGroup, registrationName)
                .concatMap(new Func1<ServiceResponse<Page<ProductInner>>, Observable<ServiceResponse<Page<ProductInner>>>>() {
                    @Override
                    public Observable<ServiceResponse<Page<ProductInner>>> call(ServiceResponse<Page<ProductInner>> page) {
                        String nextPageLink = page.body().nextPageLink();
                        if (nextPageLink == null) {
                            return Observable.just(page);
                        }
                        return Observable.just(page).concatWith(listNextWithServiceResponseAsync(nextPageLink));
                    }
                });
        }
    
        /**
         * Returns a list of products.
         *
        ServiceResponse<PageImpl<ProductInner>> * @param resourceGroup Name of the resource group.
        ServiceResponse<PageImpl<ProductInner>> * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the PagedList&lt;ProductInner&gt; object wrapped in {@link ServiceResponse} if successful.
         */
        public Observable<ServiceResponse<Page<ProductInner>>> listSinglePageAsync(final String resourceGroup, final String registrationName) {
            if (this.client.subscriptionId() == null) {
                throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
            }
            if (resourceGroup == null) {
                throw new IllegalArgumentException("Parameter resourceGroup is required and cannot be null.");
            }
            if (registrationName == null) {
                throw new IllegalArgumentException("Parameter registrationName is required and cannot be null.");
            }
            if (this.client.apiVersion() == null) {
                throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
            }
            return service.list(this.client.subscriptionId(), resourceGroup, registrationName, this.client.apiVersion(), this.client.acceptLanguage(), this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Page<ProductInner>>>>() {
                    @Override
                    public Observable<ServiceResponse<Page<ProductInner>>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<PageImpl<ProductInner>> result = listDelegate(response);
                            return Observable.just(new ServiceResponse<Page<ProductInner>>(result.body(), result.response()));
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<PageImpl<ProductInner>> listDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<PageImpl<ProductInner>, ErrorResponseException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<PageImpl<ProductInner>>() { }.getType())
                    .registerError(ErrorResponseException.class)
                    .build(response);
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws ErrorResponseException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the ProductInner object if successful.
         */
        public ProductInner get(String resourceGroup, String registrationName, String productName) {
            return getWithServiceResponseAsync(resourceGroup, registrationName, productName).toBlocking().single().body();
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<ProductInner> getAsync(String resourceGroup, String registrationName, String productName, final ServiceCallback<ProductInner> serviceCallback) {
            return ServiceFuture.fromResponse(getWithServiceResponseAsync(resourceGroup, registrationName, productName), serviceCallback);
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductInner object
         */
        public Observable<ProductInner> getAsync(String resourceGroup, String registrationName, String productName) {
            return getWithServiceResponseAsync(resourceGroup, registrationName, productName).map(new Func1<ServiceResponse<ProductInner>, ProductInner>() {
                @Override
                public ProductInner call(ServiceResponse<ProductInner> response) {
                    return response.body();
                }
            });
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductInner object
         */
        public Observable<ServiceResponse<ProductInner>> getWithServiceResponseAsync(String resourceGroup, String registrationName, String productName) {
            if (this.client.subscriptionId() == null) {
                throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
            }
            if (resourceGroup == null) {
                throw new IllegalArgumentException("Parameter resourceGroup is required and cannot be null.");
            }
            if (registrationName == null) {
                throw new IllegalArgumentException("Parameter registrationName is required and cannot be null.");
            }
            if (productName == null) {
                throw new IllegalArgumentException("Parameter productName is required and cannot be null.");
            }
            if (this.client.apiVersion() == null) {
                throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
            }
            return service.get(this.client.subscriptionId(), resourceGroup, registrationName, productName, this.client.apiVersion(), this.client.acceptLanguage(), this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ProductInner>>>() {
                    @Override
                    public Observable<ServiceResponse<ProductInner>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<ProductInner> clientResponse = getDelegate(response);
                            return Observable.just(clientResponse);
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<ProductInner> getDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<ProductInner, ErrorResponseException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<ProductInner>() { }.getType())
                    .registerError(ErrorResponseException.class)
                    .build(response);
        }
    
        /**
         * Returns the extended properties of a product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws CloudException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the ExtendedProductInner object if successful.
         */
        public ExtendedProductInner listDetails(String resourceGroup, String registrationName, String productName) {
            return listDetailsWithServiceResponseAsync(resourceGroup, registrationName, productName).toBlocking().single().body();
        }
    
        /**
         * Returns the extended properties of a product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<ExtendedProductInner> listDetailsAsync(String resourceGroup, String registrationName, String productName, final ServiceCallback<ExtendedProductInner> serviceCallback) {
            return ServiceFuture.fromResponse(listDetailsWithServiceResponseAsync(resourceGroup, registrationName, productName), serviceCallback);
        }
    
        /**
         * Returns the extended properties of a product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ExtendedProductInner object
         */
        public Observable<ExtendedProductInner> listDetailsAsync(String resourceGroup, String registrationName, String productName) {
            return listDetailsWithServiceResponseAsync(resourceGroup, registrationName, productName).map(new Func1<ServiceResponse<ExtendedProductInner>, ExtendedProductInner>() {
                @Override
                public ExtendedProductInner call(ServiceResponse<ExtendedProductInner> response) {
                    return response.body();
                }
            });
        }
    
        /**
         * Returns the extended properties of a product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ExtendedProductInner object
         */
        public Observable<ServiceResponse<ExtendedProductInner>> listDetailsWithServiceResponseAsync(String resourceGroup, String registrationName, String productName) {
            if (this.client.subscriptionId() == null) {
                throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
            }
            if (resourceGroup == null) {
                throw new IllegalArgumentException("Parameter resourceGroup is required and cannot be null.");
            }
            if (registrationName == null) {
                throw new IllegalArgumentException("Parameter registrationName is required and cannot be null.");
            }
            if (productName == null) {
                throw new IllegalArgumentException("Parameter productName is required and cannot be null.");
            }
            if (this.client.apiVersion() == null) {
                throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
            }
            return service.listDetails(this.client.subscriptionId(), resourceGroup, registrationName, productName, this.client.apiVersion(), this.client.acceptLanguage(), this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ExtendedProductInner>>>() {
                    @Override
                    public Observable<ServiceResponse<ExtendedProductInner>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<ExtendedProductInner> clientResponse = listDetailsDelegate(response);
                            return Observable.just(clientResponse);
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<ExtendedProductInner> listDetailsDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<ExtendedProductInner, CloudException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<ExtendedProductInner>() { }.getType())
                    .registerError(CloudException.class)
                    .build(response);
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws ErrorResponseException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the ProductListInner object if successful.
         */
        public ProductListInner getProducts(String resourceGroup, String registrationName, DeviceConfiguration deviceConfiguration) {
            return getProductsWithServiceResponseAsync(resourceGroup, registrationName, deviceConfiguration).toBlocking().single().body();
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<ProductListInner> getProductsAsync(String resourceGroup, String registrationName, DeviceConfiguration deviceConfiguration, final ServiceCallback<ProductListInner> serviceCallback) {
            return ServiceFuture.fromResponse(getProductsWithServiceResponseAsync(resourceGroup, registrationName, deviceConfiguration), serviceCallback);
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductListInner object
         */
        public Observable<ProductListInner> getProductsAsync(String resourceGroup, String registrationName, DeviceConfiguration deviceConfiguration) {
            return getProductsWithServiceResponseAsync(resourceGroup, registrationName, deviceConfiguration).map(new Func1<ServiceResponse<ProductListInner>, ProductListInner>() {
                @Override
                public ProductListInner call(ServiceResponse<ProductListInner> response) {
                    return response.body();
                }
            });
        }
    
        /**
         * Returns a list of products.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductListInner object
         */
        public Observable<ServiceResponse<ProductListInner>> getProductsWithServiceResponseAsync(String resourceGroup, String registrationName, DeviceConfiguration deviceConfiguration) {
            if (this.client.subscriptionId() == null) {
                throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
            }
            if (resourceGroup == null) {
                throw new IllegalArgumentException("Parameter resourceGroup is required and cannot be null.");
            }
            if (registrationName == null) {
                throw new IllegalArgumentException("Parameter registrationName is required and cannot be null.");
            }
            if (this.client.apiVersion() == null) {
                throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
            }
            return service.getProducts(this.client.subscriptionId(), resourceGroup, registrationName, this.client.apiVersion(), this.client.acceptLanguage(), deviceConfiguration, this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ProductListInner>>>() {
                    @Override
                    public Observable<ServiceResponse<ProductListInner>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<ProductListInner> clientResponse = getProductsDelegate(response);
                            return Observable.just(clientResponse);
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<ProductListInner> getProductsDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<ProductListInner, ErrorResponseException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<ProductListInner>() { }.getType())
                    .registerError(ErrorResponseException.class)
                    .build(response);
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws ErrorResponseException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the ProductInner object if successful.
         */
        public ProductInner getProduct(String resourceGroup, String registrationName, String productName, DeviceConfiguration deviceConfiguration) {
            return getProductWithServiceResponseAsync(resourceGroup, registrationName, productName, deviceConfiguration).toBlocking().single().body();
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<ProductInner> getProductAsync(String resourceGroup, String registrationName, String productName, DeviceConfiguration deviceConfiguration, final ServiceCallback<ProductInner> serviceCallback) {
            return ServiceFuture.fromResponse(getProductWithServiceResponseAsync(resourceGroup, registrationName, productName, deviceConfiguration), serviceCallback);
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductInner object
         */
        public Observable<ProductInner> getProductAsync(String resourceGroup, String registrationName, String productName, DeviceConfiguration deviceConfiguration) {
            return getProductWithServiceResponseAsync(resourceGroup, registrationName, productName, deviceConfiguration).map(new Func1<ServiceResponse<ProductInner>, ProductInner>() {
                @Override
                public ProductInner call(ServiceResponse<ProductInner> response) {
                    return response.body();
                }
            });
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductInner object
         */
        public Observable<ServiceResponse<ProductInner>> getProductWithServiceResponseAsync(String resourceGroup, String registrationName, String productName, DeviceConfiguration deviceConfiguration) {
            if (this.client.subscriptionId() == null) {
                throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
            }
            if (resourceGroup == null) {
                throw new IllegalArgumentException("Parameter resourceGroup is required and cannot be null.");
            }
            if (registrationName == null) {
                throw new IllegalArgumentException("Parameter registrationName is required and cannot be null.");
            }
            if (productName == null) {
                throw new IllegalArgumentException("Parameter productName is required and cannot be null.");
            }
            if (this.client.apiVersion() == null) {
                throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
            }
            return service.getProduct(this.client.subscriptionId(), resourceGroup, registrationName, productName, this.client.apiVersion(), this.client.acceptLanguage(), deviceConfiguration, this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ProductInner>>>() {
                    @Override
                    public Observable<ServiceResponse<ProductInner>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<ProductInner> clientResponse = getProductDelegate(response);
                            return Observable.just(clientResponse);
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<ProductInner> getProductDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<ProductInner, ErrorResponseException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<ProductInner>() { }.getType())
                    .registerError(ErrorResponseException.class)
                    .build(response);
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws ErrorResponseException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the ProductLogInner object if successful.
         */
        public ProductLogInner uploadLog(String resourceGroup, String registrationName, String productName, MarketplaceProductLogUpdate marketplaceProductLogUpdate) {
            return uploadLogWithServiceResponseAsync(resourceGroup, registrationName, productName, marketplaceProductLogUpdate).toBlocking().single().body();
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<ProductLogInner> uploadLogAsync(String resourceGroup, String registrationName, String productName, MarketplaceProductLogUpdate marketplaceProductLogUpdate, final ServiceCallback<ProductLogInner> serviceCallback) {
            return ServiceFuture.fromResponse(uploadLogWithServiceResponseAsync(resourceGroup, registrationName, productName, marketplaceProductLogUpdate), serviceCallback);
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductLogInner object
         */
        public Observable<ProductLogInner> uploadLogAsync(String resourceGroup, String registrationName, String productName, MarketplaceProductLogUpdate marketplaceProductLogUpdate) {
            return uploadLogWithServiceResponseAsync(resourceGroup, registrationName, productName, marketplaceProductLogUpdate).map(new Func1<ServiceResponse<ProductLogInner>, ProductLogInner>() {
                @Override
                public ProductLogInner call(ServiceResponse<ProductLogInner> response) {
                    return response.body();
                }
            });
        }
    
        /**
         * Returns the specified product.
         *
         * @param resourceGroup Name of the resource group.
         * @param registrationName Name of the Azure Stack registration.
         * @param productName Name of the product.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the ProductLogInner object
         */
        public Observable<ServiceResponse<ProductLogInner>> uploadLogWithServiceResponseAsync(String resourceGroup, String registrationName, String productName, MarketplaceProductLogUpdate marketplaceProductLogUpdate) {
            if (this.client.subscriptionId() == null) {
                throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
            }
            if (resourceGroup == null) {
                throw new IllegalArgumentException("Parameter resourceGroup is required and cannot be null.");
            }
            if (registrationName == null) {
                throw new IllegalArgumentException("Parameter registrationName is required and cannot be null.");
            }
            if (productName == null) {
                throw new IllegalArgumentException("Parameter productName is required and cannot be null.");
            }
            if (this.client.apiVersion() == null) {
                throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
            }
            return service.uploadLog(this.client.subscriptionId(), resourceGroup, registrationName, productName, this.client.apiVersion(), this.client.acceptLanguage(), marketplaceProductLogUpdate, this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ProductLogInner>>>() {
                    @Override
                    public Observable<ServiceResponse<ProductLogInner>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<ProductLogInner> clientResponse = uploadLogDelegate(response);
                            return Observable.just(clientResponse);
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<ProductLogInner> uploadLogDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<ProductLogInner, ErrorResponseException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<ProductLogInner>() { }.getType())
                    .registerError(ErrorResponseException.class)
                    .build(response);
        }
    
        /**
         * Returns a list of products.
         *
         * @param nextPageLink The NextLink from the previous successful call to List operation.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @throws ErrorResponseException thrown if the request is rejected by server
         * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
         * @return the PagedList&lt;ProductInner&gt; object if successful.
         */
        public PagedList<ProductInner> listNext(final String nextPageLink) {
            ServiceResponse<Page<ProductInner>> response = listNextSinglePageAsync(nextPageLink).toBlocking().single();
            return new PagedList<ProductInner>(response.body()) {
                @Override
                public Page<ProductInner> nextPage(String nextPageLink) {
                    return listNextSinglePageAsync(nextPageLink).toBlocking().single().body();
                }
            };
        }
    
        /**
         * Returns a list of products.
         *
         * @param nextPageLink The NextLink from the previous successful call to List operation.
         * @param serviceFuture the ServiceFuture object tracking the Retrofit calls
         * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the {@link ServiceFuture} object
         */
        public ServiceFuture<List<ProductInner>> listNextAsync(final String nextPageLink, final ServiceFuture<List<ProductInner>> serviceFuture, final ListOperationCallback<ProductInner> serviceCallback) {
            return AzureServiceFuture.fromPageResponse(
                listNextSinglePageAsync(nextPageLink),
                new Func1<String, Observable<ServiceResponse<Page<ProductInner>>>>() {
                    @Override
                    public Observable<ServiceResponse<Page<ProductInner>>> call(String nextPageLink) {
                        return listNextSinglePageAsync(nextPageLink);
                    }
                },
                serviceCallback);
        }
    
        /**
         * Returns a list of products.
         *
         * @param nextPageLink The NextLink from the previous successful call to List operation.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the PagedList&lt;ProductInner&gt; object
         */
        public Observable<Page<ProductInner>> listNextAsync(final String nextPageLink) {
            return listNextWithServiceResponseAsync(nextPageLink)
                .map(new Func1<ServiceResponse<Page<ProductInner>>, Page<ProductInner>>() {
                    @Override
                    public Page<ProductInner> call(ServiceResponse<Page<ProductInner>> response) {
                        return response.body();
                    }
                });
        }
    
        /**
         * Returns a list of products.
         *
         * @param nextPageLink The NextLink from the previous successful call to List operation.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the observable to the PagedList&lt;ProductInner&gt; object
         */
        public Observable<ServiceResponse<Page<ProductInner>>> listNextWithServiceResponseAsync(final String nextPageLink) {
            return listNextSinglePageAsync(nextPageLink)
                .concatMap(new Func1<ServiceResponse<Page<ProductInner>>, Observable<ServiceResponse<Page<ProductInner>>>>() {
                    @Override
                    public Observable<ServiceResponse<Page<ProductInner>>> call(ServiceResponse<Page<ProductInner>> page) {
                        String nextPageLink = page.body().nextPageLink();
                        if (nextPageLink == null) {
                            return Observable.just(page);
                        }
                        return Observable.just(page).concatWith(listNextWithServiceResponseAsync(nextPageLink));
                    }
                });
        }
    
        /**
         * Returns a list of products.
         *
        ServiceResponse<PageImpl<ProductInner>> * @param nextPageLink The NextLink from the previous successful call to List operation.
         * @throws IllegalArgumentException thrown if parameters fail the validation
         * @return the PagedList&lt;ProductInner&gt; object wrapped in {@link ServiceResponse} if successful.
         */
        public Observable<ServiceResponse<Page<ProductInner>>> listNextSinglePageAsync(final String nextPageLink) {
            if (nextPageLink == null) {
                throw new IllegalArgumentException("Parameter nextPageLink is required and cannot be null.");
            }
            String nextUrl = String.format("%s", nextPageLink);
            return service.listNext(nextUrl, this.client.acceptLanguage(), this.client.userAgent())
                .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Page<ProductInner>>>>() {
                    @Override
                    public Observable<ServiceResponse<Page<ProductInner>>> call(Response<ResponseBody> response) {
                        try {
                            ServiceResponse<PageImpl<ProductInner>> result = listNextDelegate(response);
                            return Observable.just(new ServiceResponse<Page<ProductInner>>(result.body(), result.response()));
                        } catch (Throwable t) {
                            return Observable.error(t);
                        }
                    }
                });
        }
    
        private ServiceResponse<PageImpl<ProductInner>> listNextDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
            return this.client.restClient().responseBuilderFactory().<PageImpl<ProductInner>, ErrorResponseException>newInstance(this.client.serializerAdapter())
                    .register(200, new TypeToken<PageImpl<ProductInner>>() { }.getType())
                    .registerError(ErrorResponseException.class)
                    .build(response);
        }
    
    }
}
