package com.curso.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CriarActivity extends AppCompatActivity {
    SQLiteDatabase bancoDados;
    private Button btnSalvar;
    private Button btnCancelar;
    private EditText mtitulo;
    private EditText mtarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar);
        btnCancelar = (Button)findViewById(R.id.brn_cancelar_id);
        btnSalvar = (Button)findViewById(R.id.btn_gravar_id);
        bancoDados = openOrCreateDatabase("appTarefa",MODE_PRIVATE,null);
        mtitulo = (EditText)findViewById(R.id.editText_titulo_id);
        mtarefa = (EditText)findViewById(R.id.editText_tarefa_id);
        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent cancelaGravacao = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(cancelaGravacao);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo, tarefa, msgToast;
                titulo = tarefa = null;
                msgToast = "Verifique se o título e a tarefa foram digitados corretamente.";
                titulo = mtitulo.getText().toString();
                tarefa = mtarefa.getText().toString();
//              Testa se existe texto digitado no titulo e tarefa
                if (titulo.equals("") || tarefa.equals("")){
                    Toast.makeText(CriarActivity.this, msgToast, Toast.LENGTH_SHORT).show();
                    if (titulo.equals(""))
                        mtitulo.setBackgroundColor(Color.parseColor("#f44336"));
                    if (tarefa.equals(""))
                        mtarefa.setBackgroundColor(Color.parseColor("#f44336"));
                }
                else{
                    try {
                        bancoDados.execSQL("INSERT INTO tabela (nome, tarefa) VALUES ('" + titulo + "','" + tarefa + "')");
                        mtitulo.setText("");
                        mtarefa.setText("");
                        Toast.makeText(CriarActivity.this, "Tarefa gravada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        mtitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtitulo.getDrawingCacheBackgroundColor()!=Color.parseColor("#ffffff"))
                    mtitulo.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        mtarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtarefa.getDrawingCacheBackgroundColor()!=Color.parseColor("#ffffff"))
                    mtarefa.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
    }
}
