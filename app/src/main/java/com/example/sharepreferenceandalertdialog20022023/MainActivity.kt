package com.example.sharepreferenceandalertdialog20022023

import android.accessibilityservice.GestureDescription
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var rlt: RelativeLayout
    private lateinit var storeemail: TextView
    private lateinit var storepass: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var butTon: Button
    private lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.edit_text_1)
        pass=findViewById(R.id.edit_text_2)
        rlt=findViewById(R.id.layout_view3)
        storepass=findViewById(R.id.text_tol_2)
        storeemail=findViewById(R.id.text_tol_1)
        checkBox=findViewById(R.id.check_box)
        butTon=findViewById(R.id.bt_dangnhap)
        image=findViewById(R.id.image_delete)
        butTon.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                val edit1=email.text.toString()
                val edit2=pass.text.toString()
                if (edit1.isEmpty()|| edit2.isEmpty()){
                    Toast.makeText(this@MainActivity, "Bạn chưa nhập đầy đủ!!", Toast.LENGTH_SHORT).show()
                    return
                }
                if (edit1=="abcp@gmail.com"&& edit2=="abc"){
                    if (checkBox.isChecked()){

                        Toast.makeText(this@MainActivity,"Đăng nhập thành cônng",Toast.LENGTH_SHORT).show()
                        Toast.makeText(this@MainActivity,"Đã lưu thông tin",Toast.LENGTH_SHORT).show()
                        rlt.visibility= View.VISIBLE
                        val tk=email.text.toString()
                        val mk=pass.text.toString()
                        storeemail.setText("Email: $tk")
                        storepass.setText("Pass: $mk")

                    }
                    else
                        Toast.makeText(this@MainActivity,"Đăng nhập thành cônng",Toast.LENGTH_SHORT).show()
                    email.setText("")
                    pass.setText("")
                }
                else
                    Toast.makeText(this@MainActivity,"Đăng nhập không thành cônng",Toast.LENGTH_SHORT).show()


            }

        })
        image.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                val b: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                b.setTitle("Thông báo")
                b.setMessage("Bạn có muốn xóa thông tin lưu trữ")
                b.setPositiveButton("Có", DialogInterface.OnClickListener { dialog, id ->
                        rlt.visibility= View.GONE
                        Toast.makeText(this@MainActivity,"Xóa lưu đăng nhập thành công",Toast.LENGTH_SHORT).show()
                        storeemail.setText("")
                        storepass.setText("")
                    })
                b.setNegativeButton("Không",DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                val al: AlertDialog = b.create()
                al.show()

            }

        })
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences=getSharedPreferences("Account", MODE_PRIVATE)
        val myEdit=sharedPreferences.edit()

        myEdit.putString("email",storeemail.text.toString())
        myEdit.putString("pass",storepass.text.toString())
        myEdit.apply()
    }

    override fun onResume() {
        super.onResume()

        val sh= getSharedPreferences("Account", MODE_PRIVATE)
        val e=sh.getString("email","")
        val p=sh.getString("pass","")
        if (e!="" && p!=""){
            storeemail.setText("Email: $e")
            storepass.setText("Pass: $p")
        }

        if (storeemail.text.toString()!="" && storeemail.text.toString()!=""){
            rlt.visibility= View.VISIBLE
        }
    }
}