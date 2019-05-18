package com.kishore.news.model.worker

//class NewsRxWorker(appContext: Context, params: WorkerParameters) :
//        RxWorker(appContext, params) {
//
//
//    override fun createWork(): Single<Result> {
//        val networkDataSource = InjectorUtils.provideNetworkDataSource(this.applicationContext)
//        networkDataSource.getHeadlinesFromNetwork()
//        networkDataSource.getHeadlines()
//
//        Single< Result> result = Single.create<Result> { emitter -
//
//                // do the job
//
//                emitter.onSuccess(Result.success())
//        }
//    }
//
//}
