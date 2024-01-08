package mx.ogr.practica11

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class LoginControl : LinearLayout {
    private lateinit var txtUsuario : EditText
    private lateinit var txtPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var lblMensaje : TextView

    constructor(ctx: Context) : super(ctx){
        inicializar()
    }
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs){
        inicializar()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ControlLogin, 0, 0).apply {
                try{
                    btnLogin.text = getString(R.styleable.ControlLogin_login_text)
                } finally {
                    recycle()
                }
        }
    }
    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr){
        inicializar()
    }

    fun inicializar(){
        //Utilizamos el layout 'control_login' como interfaz del control
        val li = LayoutInflater.from(context)
        li.inflate(R.layout.login_control, this, true)

        //Obtenemoslas referencias a los distintos controles
        txtUsuario = findViewById(R.id.txtUsuario) as EditText
        txtPassword = findViewById(R.id.txtPassword) as EditText
        btnLogin = findViewById(R.id.btnAceptar) as Button
        lblMensaje = findViewById(R.id.lblMensaje) as TextView

        asignarEventos()
    }
    var listener: OnLoginListener? = null
    fun setOnLoginListener(login: (String, String) -> Unit){
        listener = object:OnLoginListener{
            override fun onLogin(usuario: String, password: String) {
                login(usuario, password)
            }
        }
    }
    fun asignarEventos(){
        btnLogin.setOnClickListener{
            listener?.onLogin(txtUsuario.text.toString(),
                        txtPassword.text.toString())
        }
    }


    fun setMensaje(msg: String){
        lblMensaje.text = msg
    }
}