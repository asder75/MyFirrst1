package com.example.myfirrst

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Math.cos
import java.lang.Math.sin




class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    private  val FILE_NAME = "photo.jpg"
    private  val REQUEST_CODE = 42
    private lateinit var photoFile: File

    @SuppressLint("WrongViewCast")

    override fun onBackPressed() {
        //алертдиалог
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Внимание!")
        builder.setMessage("Вы хотите закрыть приложение?")
        builder.setPositiveButton("Да", { dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        builder.setNegativeButton("Нет",{ dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         lottie1.setAnimation("gradback.json")

        lottie1.playAnimation()
        lottie1.loop(true)

       // val button: Button = findViewById<View>(R.id.buttonrotate2) as Button

        ActivityCompat.requestPermissions(this,
         arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
         Manifest.permission.WRITE_EXTERNAL_STORAGE),
        100)

        //123
        val image: ImageView = findViewById(R.id.image_view) as ImageView

        var bitmap = (image.drawable as BitmapDrawable).bitmap
        var newBitmap: Bitmap
        var newImage: IntArray = IntArray(bitmap.getWidth()* bitmap.getHeight())


        val firstFragment = FirstFragment()
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().add(R.id.linearLayout, firstFragment).commit()
        val  mp = MediaPlayer.create(this,R.raw.lesgosound)

        var degree = 45



        buttonrotate2.setOnClickListener {
mp.start()
            //println(bitmap.getHeight())
            //println(bitmap.getWidth())
degree = 45
            newBitmap = rotateBonus(bitmap, degree)
            image.setImageBitmap(newBitmap)
            bitmap = newBitmap
            println(bitmap.getHeight())
            println(bitmap.getWidth())

        }








       // }

        //123

       val fileName = "my_file.jpg"
         val path = this.getExternalFilesDir(null)!!.absolutePath
        val file = File(path, fileName)


       btnTakePicture.setOnClickListener {
           mp.start()
          val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      photoFile = getPhotoFile(FILE_NAME)

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)
            val fileProvider =   FileProvider.getUriForFile(this, "com.example.myfirrst.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if(takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            }else{
                Toast.makeText(this,"Не получилось открыть камеру", Toast.LENGTH_SHORT).show()
            }

        }

        //кнопка для сохранения пикчи в галерею
        saveButton.setOnClickListener {
            mp.start()
            val imgBitmap = image_view.drawable.toBitmap()
            try{
                val stream = FileOutputStream(file)
                imgBitmap.compress(Bitmap.CompressFormat.JPEG,
                    100,
                    stream)
                stream.flush()
                stream.close()
                Toast.makeText(this,
                    "Image Saved",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            catch(e: IOException)
            {
                e.printStackTrace()
            }
        }
        //кнопка для загрузки пикчи
        img_pick_btn.setOnClickListener {
            mp.start()
            //проверка запуска разрешений//
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    //в разрешениях отказали
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    // вспл.окно для запроса времени выполнения
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //права есть урааа!
                    pickImageFromGallery();

                }
            } else {
                //
                pickImageFromGallery();
            }
        }



        //кнопки поворота на 90 градусов
        buttonrotate.setOnClickListener {
            mp.start()
            editext1.setVisibility(View.VISIBLE);
            butgalka.setVisibility(View.VISIBLE);
            textgradusi.setVisibility(View.VISIBLE);


        }

        butgalka.setOnClickListener {
            mp.start()
            editext1.setVisibility(View.INVISIBLE);
            butgalka.setVisibility(View.INVISIBLE);
            textgradusi.setVisibility(View.INVISIBLE);
            editText = findViewById<View>(R.id.editext1) as EditText

             val agradusx = editText.text.toString()
            val anyName = agradusx.toInt()
            degree = anyName
            newBitmap = rotateBonus(bitmap, degree)
            image.setImageBitmap(newBitmap)
            bitmap = newBitmap
            println(bitmap.getHeight())
            println(bitmap.getWidth())


            //textView44.setText(agradusx.toString())


        }

        buttonfilter.setOnClickListener {
            mp.start()
            buttonegatiw.setVisibility(View.VISIBLE)
            buttonreskost.setVisibility(View.VISIBLE)
            buttoncb.setVisibility(View.VISIBLE)
            buttonexitfilter.setVisibility(View.VISIBLE)


        }

        buttonexitfilter.setOnClickListener {
            mp.start()
            buttonegatiw.setVisibility(View.INVISIBLE)
            buttonreskost.setVisibility(View.INVISIBLE)
            buttoncb.setVisibility(View.INVISIBLE)
            buttonexitfilter.setVisibility(View.INVISIBLE)
            cb1.setVisibility(View.INVISIBLE)
            cb2.setVisibility(View.INVISIBLE)
            cb3.setVisibility(View.INVISIBLE)
            cb4.setVisibility(View.INVISIBLE)
        }
        cb1.setOnClickListener {
            mp.start()
            bitmap = blackWhite(bitmap, "median")
            image.setImageBitmap(bitmap)
        }
        cb2.setOnClickListener {
            mp.start()
            bitmap = blackWhite(bitmap, "red")
            image.setImageBitmap(bitmap)
        }
        cb3.setOnClickListener {
            mp.start()
            bitmap = blackWhite(bitmap, "green")
            image.setImageBitmap(bitmap)
        }
        cb4.setOnClickListener {
            mp.start()
            bitmap = blackWhite(bitmap, "blue")
            image.setImageBitmap(bitmap)
        }


        //кнопка негатив
        buttonegatiw.setOnClickListener {
            mp.start()
        bitmap = negative(bitmap)
        image.setImageBitmap(bitmap)
        }
        //кнопка контраст
        buttonreskost.setOnClickListener {
            mp.start()
        bitmap = contrst(bitmap, 0.5F)
        image.setImageBitmap(bitmap)
        }
      //кнопка черно-белого
        buttoncb.setOnClickListener {
            mp.start()

            cb1.setVisibility(View.VISIBLE)
            cb2.setVisibility(View.VISIBLE)
            cb3.setVisibility(View.VISIBLE)
            cb4.setVisibility(View.VISIBLE)
        }
        buttoncub.setOnClickListener {

        }

    }

    private fun getPhotoFile(fileName: String): File {
       val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
      return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    //функция получения изображения из галереи
    private fun pickImageFromGallery()
    {
val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    companion object {
      //код взятого изображения
        private val IMAGE_PICK_CODE = 1000
        //код для разрешения
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {




       when(requestCode)
       {
         PERMISSION_CODE -> {
             if (grantResults.size >0 && grantResults[0] ==
                             PackageManager.PERMISSION_GRANTED)
             {
                //разр-я на вспл-е окна предоставлены
                 pickImageFromGallery()
             }
             else
             {
                 //разрешения на вспл-е окна отказаны

                 Toast.makeText(this, "Вы отказали в правах", Toast.LENGTH_SHORT).show()
             }
         }
       }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            image_view.setImageURI(data?.data)

        }

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
      // val takenImage = data?.extras?.get("data") as Bitmap
           val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            image_view.setImageBitmap(takenImage)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    // 1st task
    fun rotate(bitmap: Bitmap): Bitmap {
        val image: ImageView = findViewById(R.id.image_view) as ImageView


        var bitmap = (image.drawable as BitmapDrawable).bitmap
        var newBitmap: Bitmap
        var newImage: IntArray = IntArray(bitmap.getWidth()* bitmap.getHeight())
        val width: Int = bitmap.getWidth()
        val height: Int = bitmap.getHeight()

        newBitmap = Bitmap.createBitmap(height, width, bitmap.config)
        var pixel: Int
        for (x in 0 until width) {
            for (y in 0 until height) {
                pixel = bitmap.getPixel(x, y)
                newBitmap.setPixel(y, width - 1 - x, pixel)
            }
        }

        return newBitmap
    }


    fun rotateBonus(bitmap: Bitmap, degree: Int): Bitmap {
        val image: ImageView = findViewById(R.id.image_view) as ImageView


        var bitmap = (image.drawable as BitmapDrawable).bitmap
        var newBitmap: Bitmap
        var newImage: IntArray = IntArray(bitmap.getWidth()* bitmap.getHeight())
        val width = bitmap.getWidth()
        val height = bitmap.getHeight()
        val pi = 3.1415926
        val radian = (2*pi*degree) / 360
        val cos = cos(radian)
        val sin = sin(radian)

        val secondAngleX  = -height*sin
        val secondAngleY  = height*cos
        val thirdAngleX = width*cos - height*sin
        val thirdAngleY = width*sin + height*cos
        val forthAngleX = width*cos
        val forthAngleY = width*sin
        val minX: Double = minimum(0.0, minimum(secondAngleX, minimum(thirdAngleX, forthAngleX)))
        val minY: Double = minimum(0.0, minimum(secondAngleY, minimum(thirdAngleY, forthAngleY)))
        val maxX: Double = maximum(secondAngleX, maximum(thirdAngleX, forthAngleX))
        val maxY: Double = maximum(secondAngleY, maximum(thirdAngleY, forthAngleY))
        val newWidth: Int = upper((module(maxX) - minX))
        val newHeight: Int = upper((module(maxY) - minY))

        newBitmap = Bitmap.createBitmap(newWidth, newHeight, bitmap.config)
        var pixel: Int
        for (x in 0 until newWidth) {
            for (y in 0 until newHeight) {
                var oldx: Int = ((x + minX)*cos + (y + minY)*sin).toInt()
                var oldy: Int = ((y + minY)*cos - (x + minX)*sin).toInt()
                if ( oldx >= 0 && oldx < width && oldy >= 0 && oldy < height ) {
                    pixel = bitmap.getPixel(oldx, oldy)
                    newBitmap.setPixel(x, y, pixel)
                }
            }
        }

        return newBitmap
    }

    fun upper(x: Double):Int {
        if(x.toInt() < x) {
            return (x.toInt() + 1)
        } else {
            return x.toInt()
        }
    }

    fun minimum(a: Double, b: Double):Double {
        if (a < b) {
            return a
        } else {
            return b
        }
    }

    fun maximum(a: Double, b: Double):Double {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun module(x: Double): Double {
        if (x < 0) {
            return -x
        } else {
            return x
        }
    }

    // 2nd task
    fun negative(bitmap: Bitmap): Bitmap {
        val image: ImageView = findViewById(R.id.image_view) as ImageView


        var bitmap = (image.drawable as BitmapDrawable).bitmap
        var newBitmap: Bitmap
        var newImage: IntArray = IntArray(bitmap.getWidth()* bitmap.getHeight())

        val width = bitmap.getWidth()
        val height = bitmap.getHeight()
        var pixel: Int
        var alphaColor: Int
        var redColor: Int
        var greenColor: Int
        var blueColor: Int

        newBitmap = Bitmap.createBitmap(width, height, bitmap.config)
        for (x in 0 until width) {
            for (y in 0 until height) {
                pixel = bitmap.getPixel(x, y)
                alphaColor = Color.alpha(pixel)
                redColor = 255 - Color.red(pixel)
                greenColor = 255 - Color.green(pixel)
                blueColor = 255 - Color.blue(pixel)
                pixel = Color.argb(alphaColor, redColor, greenColor, blueColor)

                newBitmap.setPixel(x, y, pixel)
            }
        }

        return newBitmap
    }

    fun blackWhite(bitmap: Bitmap, chanelColor: String): Bitmap {
        val width = bitmap.getWidth()
        val height = bitmap.getHeight()
        var pixel: Int
        var newBitmap: Bitmap
        val image: ImageView = findViewById(R.id.image_view) as ImageView


        var bitmap = (image.drawable as BitmapDrawable).bitmap

        var newImage: IntArray = IntArray(bitmap.getWidth()* bitmap.getHeight())

        newBitmap = Bitmap.createBitmap(width, height, bitmap.config)
        if (chanelColor === "red") {
            for (i in 0 until width) {
                for (j in 0 until height) {
                    pixel = bitmap.getPixel(i, j)
                    pixel = Color.argb(Color.alpha(pixel), Color.red(pixel), Color.red(pixel), Color.red(pixel))
                    newBitmap.setPixel(i, j, pixel)
                }
            }
        } else if (chanelColor === "green") {
            for (i in 0 until width) {
                for (j in 0 until height) {
                    pixel = bitmap.getPixel(i, j)
                    pixel = Color.argb(Color.alpha(pixel), Color.green(pixel), Color.green(pixel), Color.green(pixel))
                    newBitmap.setPixel(i, j, pixel)
                }
            }
        } else if (chanelColor === "blue"){
            for (i in 0 until width) {
                for (j in 0 until height) {
                    pixel = bitmap.getPixel(i, j)
                    pixel = Color.argb(Color.alpha(pixel), Color.blue(pixel), Color.blue(pixel), Color.blue(pixel))
                    newBitmap.setPixel(i, j, pixel)
                }
            }
        } else if (chanelColor === "median") {
            var mediumColor: Int
            for (i in 0 until width) {
                for (j in 0 until height) {
                    pixel = bitmap.getPixel(i, j)
                    mediumColor = (Color.red(pixel) + Color.green(pixel) + Color.blue(pixel)) / 3
                    pixel = Color.argb(Color.alpha(pixel), mediumColor, mediumColor, mediumColor)
                    newBitmap.setPixel(i, j, pixel)
                }
            }
        }

        return newBitmap
    }

    fun contrst(bitmap: Bitmap, k: Float): Bitmap {
        val image: ImageView = findViewById(R.id.image_view) as ImageView


        var bitmap = (image.drawable as BitmapDrawable).bitmap
        var newBitmap: Bitmap
        var newImage: IntArray = IntArray(bitmap.getWidth()* bitmap.getHeight())

        val width = bitmap.getWidth()
        val height = bitmap.getHeight()

        var redColor: Float
        var greenColor: Float
        var blueColor: Float

        var redColorMedian: Int = 0
        var greenColorMedian: Int = 0
        var blueColorMedian: Int = 0

        newBitmap = Bitmap.createBitmap(width, height, bitmap.config)
        for (i in 0 until width) {
            for (j in 0 until height) {
                var pixel = bitmap.getPixel(i, j)
                redColorMedian += Color.red(pixel)
                greenColorMedian+= Color.green(pixel)
                blueColorMedian += Color.blue(pixel)
            }
        }
        redColorMedian = redColorMedian / (width * height)
        greenColorMedian = greenColorMedian / (width * height)
        blueColorMedian = blueColorMedian / (width * height)

        for (i in 0 until width) {
            for (j in 0 until height) {
                var pixel = bitmap.getPixel(i, j)
                redColor = k*(Color.red(pixel) - redColorMedian) + redColorMedian
                greenColor = k*(Color.green(pixel) - greenColorMedian) + greenColorMedian
                blueColor = k*(Color.blue(pixel) - blueColorMedian) + blueColorMedian

                if(redColor < 0) {
                    redColor = 0F
                } else if (redColor > 255) {
                    redColor = 255F
                }

                if(greenColor < 0) {
                    greenColor = 0F
                } else if (greenColor > 255) {
                    greenColor = 255F
                }
                if(blueColor < 0) {
                    blueColor = 0F
                } else if (blueColor > 255) {
                    blueColor = 255F
                }

                pixel = Color.argb(Color.alpha(pixel), redColor.toInt(), greenColor.toInt(), blueColor.toInt())
                newBitmap.setPixel(i, j, pixel)
            }
        }
        return newBitmap
    }

private fun attachFragment(fragment: Fragment, tag: String) {
    if(fragment.isDetached) {
        supportFragmentManager.beginTransaction().attach(fragment).commit()
    } else {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag).commit()
    }
    supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
}

}