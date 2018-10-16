package com.danielebottillo.blog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MainActivityView {

    val textView: TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.text) }
    val working: Button by lazy(LazyThreadSafetyMode.NONE) { findViewById<Button>(R.id.working) }
    val broken: Button by lazy(LazyThreadSafetyMode.NONE) { findViewById<Button>(R.id.broken) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = MainActivityPresenter().apply {
            init(this@MainActivity)
        }

        working.setOnClickListener {
            presenter.loadWithDelay()
        }

        broken.setOnClickListener {
            presenter.loadWithDelayBroken()
        }
    }

    override fun showText(text: String) {
        textView.text = text
    }
}

class MainActivityPresenter {

    private lateinit var view: MainActivityView

    fun init(view: MainActivityView) {
        this.view = view
    }

    fun load() {
        Log.e("TAG", "load called")
        Single.fromCallable {
            Log.e("TAG", "running on " + Thread.currentThread().name)
            Thread.sleep(DELAY)
            "done!"
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.e("TAG", "doOnSubscribe " + Thread.currentThread().name)
                    view.showText("started!")
                }
                .doOnSuccess {
                    Log.e("TAG", "doOnSuccess " + Thread.currentThread().name)
                    view.showText(it)
                }
                .doOnError {
                    Log.e("TAG", "error $it")
                    view.showText(it.localizedMessage)
                }
                .subscribe()
    }

    fun loadWithDelay() {
        Log.e("TAG", "load called with delay")
        Single.fromCallable {
            Log.e("TAG", "running on " + Thread.currentThread().name)
            "done!"
        }
                .subscribeOn(Schedulers.io())
                .delay(DELAY, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.e("TAG", "doOnSubscribe " + Thread.currentThread().name)
                    view.showText("started!")
                }
                .doOnSuccess {
                    Log.e("TAG", "doOnSuccess " + Thread.currentThread().name)
                    view.showText(it)
                }
                .doOnError {
                    Log.e("TAG", "error $it")
                    view.showText(it.localizedMessage)
                }
                .subscribe()
    }

    fun loadWithDelayBroken() {
        Log.e("TAG", "load called with delay broken")
        Single.fromCallable {
            Log.e("TAG", "running on " + Thread.currentThread().name)
            "done!"
        }
                .subscribeOn(Schedulers.io())
                .delay(DELAY, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.e("TAG", "doOnSubscribe " + Thread.currentThread().name)
                    view.showText("started!")
                }
                .doOnSuccess {
                    Log.e("TAG", "doOnSuccess " + Thread.currentThread().name)
                    view.showText(it)
                }
                .doOnError {
                    Log.e("TAG", "error $it")
                    view.showText(it.localizedMessage)
                }
                .subscribe()
    }
}

interface MainActivityView {
    fun showText(text: String)
}

val DELAY = 1000L
val REPEAT = 2L