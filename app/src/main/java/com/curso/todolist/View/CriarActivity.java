package com.curso.todolist.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.curso.todolist.Model.Task;
import com.curso.todolist.Persistence.TaskRepository;
import com.curso.todolist.R;

public class CriarActivity extends AppCompatActivity {

    TaskRepository mTaskRepository;
    private EditText mTitle;
    private EditText mResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar);

        Button btnCancelar = (Button) findViewById(R.id.brn_cancelar_id);
        Button btnSalvar = (Button) findViewById(R.id.btn_gravar_id);
        mTitle = (EditText) findViewById(R.id.editText_titulo_id);
        mResume = (EditText) findViewById(R.id.editText_tarefa_id);

        mTaskRepository = new TaskRepository(getApplicationContext());

        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent cancelButton = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancelButton);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResume.getText().toString().isEmpty() || mTitle.getText().toString().isEmpty())
                    ToastMessage("Atenção. Todos os campos procisam ser preenchidos");
                else {
                    try {
                        Task TmpTask = new Task(mTitle.getText().toString(), mResume.getText().toString());
                        mTaskRepository.Save(TmpTask);
                        CleanEditText();
                        ToastMessage("Tarefa gravada com sucesso");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    public void CleanEditText() {
        mTitle.setText("");
        mResume.setText("");
    }

    public void ToastMessage(String message) {
        Toast.makeText(CriarActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
