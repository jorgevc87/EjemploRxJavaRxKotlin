package com.project.ejemplorxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        findViewById<View>(R.id.btn).setOnClickListener {
            Toast.makeText(this@MainActivity, "Start RX", Toast.LENGTH_SHORT).show()

            //EJEMPLO CON RXJAVA
            //startRxJava()

            //EJEMPLO CON RXKOTLIN
            startRxKotlin()
        }
    }

    private fun startRxKotlin() {
        //CREANDO UN LIST NORMAL
        val list = listOf<String>("1", "2", "3", "4", "5")

        //CONVIRTIENDO LA LISTA A OBSERVABLE Y SUSCRIBIENDOLA
        list.toObservable()
            .subscribeBy(
                onNext = {
                    Log.i("Observer", "Valor recibido $it")
                },
                onError = {
                    it.printStackTrace()
                },
                onComplete = {
                    Log.i("Observer", "Observer OnComplete")
                }
            )
    }

    private fun startRxJava() {
        //CREAR EL OBSERVABLE
        val observable = getObservable()

        //CREAR EL OBSERVER
        val observer = getObserver()

        //SUSCRIBIENDO EL OBSERVER AL OBSERVABLE
        observable.subscribe(observer)
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                Log.i("Observer", "Observer OnComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.i("Observer", "Observer suscrito")
            }

            override fun onNext(t: String) {
                Log.i("Observer", "Valor recibido: $t")
            }

            override fun onError(e: Throwable) {
                Log.i("Observer", "Ocurrioó")
            }
        }
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }
}
