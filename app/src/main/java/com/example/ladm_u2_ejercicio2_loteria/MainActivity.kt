package com.example.ladm_u2_ejercicio2_loteria

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ladm_u2_ejercicio2_loteria.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var listanum= arrayListOf<Int>()
        var l2=arrayListOf<Int>()
        val audios= arrayOf(
            R.raw.audio1,
            R.raw.audio2,
            R.raw.audio3,
            R.raw.audio4,
            R.raw.audio5,
            R.raw.audio6,
            R.raw.audio7,
            R.raw.audio8,
            R.raw.audio9,
            R.raw.audio10,
            R.raw.audio11,
            R.raw.audio12,
            R.raw.audio13,
            R.raw.audio14,
            R.raw.audio15,
            R.raw.audio16,
            R.raw.audio17,
            R.raw.audio18,
            R.raw.audio19,
            R.raw.audio20,
            R.raw.audio21,
            R.raw.audio22,
            R.raw.audio23,
            R.raw.audio24,
            R.raw.audio25,
            R.raw.audio26,
            R.raw.audio27,
            R.raw.audio28,
            R.raw.audio29,
            R.raw.audio30,
            R.raw.audio31,
            R.raw.audio32,
            R.raw.audio33,
            R.raw.audio34,
            R.raw.audio35,
            R.raw.audio36,
            R.raw.audio37,
            R.raw.audio38,
            R.raw.audio39,
            R.raw.audio40,
            R.raw.audio41,
            R.raw.audio42,
            R.raw.audio43,
            R.raw.audio44,
            R.raw.audio45,
            R.raw.audio46,
            R.raw.audio47,
            R.raw.audio48,
            R.raw.audio49,
            R.raw.audio50,
            R.raw.audio51,
            R.raw.audio52,
            R.raw.audio53,
            R.raw.audio54
        )
        val imagenes= arrayOf(
            R.drawable.carta1,
            R.drawable.carta2,
            R.drawable.carta3,
            R.drawable.carta4,
            R.drawable.carta5,
            R.drawable.carta6,
            R.drawable.carta7,
            R.drawable.carta8,
            R.drawable.carta9,
            R.drawable.carta10,
            R.drawable.carta11,
            R.drawable.carta12,
            R.drawable.carta13,
            R.drawable.carta14,
            R.drawable.carta15,
            R.drawable.carta16,
            R.drawable.carta17,
            R.drawable.carta18,
            R.drawable.carta19,
            R.drawable.carta20,
            R.drawable.carta21,
            R.drawable.carta22,
            R.drawable.carta23,
            R.drawable.carta24,
            R.drawable.carta25,
            R.drawable.carta26,
            R.drawable.carta27,
            R.drawable.carta28,
            R.drawable.carta29,
            R.drawable.carta30,
            R.drawable.carta31,
            R.drawable.carta32,
            R.drawable.carta33,
            R.drawable.carta34,
            R.drawable.carta35,
            R.drawable.carta36,
            R.drawable.carta37,
            R.drawable.carta38,
            R.drawable.carta39,
            R.drawable.carta40,
            R.drawable.carta41,
            R.drawable.carta42,
            R.drawable.carta43,
            R.drawable.carta44,
            R.drawable.carta45,
            R.drawable.carta46,
            R.drawable.carta47,
            R.drawable.carta48,
            R.drawable.carta49,
            R.drawable.carta50,
            R.drawable.carta51,
            R.drawable.carta52,
            R.drawable.carta53,
            R.drawable.carta54
        )



        fun eliminar(num:Int){
            l2.removeAt(num)

        }
        fun reproducir(valor: Int){
            var mp = MediaPlayer.create(this,audios[valor])
            mp.start()

        }
        fun LanzarCarta(contador:Int)
        {
            binding.imagen.setImageResource(imagenes[listanum[contador]])
            reproducir(listanum[contador])
            eliminar(contador)

        }
        var cadena=""
        var contador=0
        var pausa=false
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        val objetoCoroutineControlada = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
            while (true){
                runOnUiThread {
                    if(!pausa){

                        LanzarCarta(contador++)

                    }
                }
                delay(2000L)
                if(contador==53)pausa=true
            }

        }
        binding.btn1.setOnClickListener {

            if (objetoCoroutineControlada.isActive){
                if(pausa==true)
                {
                    pausa=false
                }
                else
                {
                    pausa=true
                }
                return@setOnClickListener
            }

            objetoCoroutineControlada.start()
        }



        fun generarMazo(){
            var num =0
            while (listanum.size<54)
            {
             num=Random.nextInt(54)
                if(listanum.contains(num)){

                }else{
                    listanum.add(num)
                }
            }
            l2.addAll(listanum)

        }


        binding.btnbarajear.setOnClickListener {
            listanum.clear()
            generarMazo()
        }

        binding.btn2.setOnClickListener {
            objetoCoroutineControlada.cancel()
            val hilo=Hilo(binding.imagen,l2)
        hilo.start()

        }


    }

}