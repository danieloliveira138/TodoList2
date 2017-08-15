package com.curso.todolist.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.curso.todolist.Controler.Task;
import com.curso.todolist.Model.TaskRepository;
import com.curso.todolist.R;

public class CriarActivity extends AppCompatActivity {

    TaskRepository mTaskRepository;
    private EditText mTitle;
    private EditText mResume;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar);

        Button btnCancelar = (Button) findViewById(R.id.brn_cancelar_id);
        Button btnSalvar = (Button) findViewById(R.id.btn_gravar_id);
        mTitle = (EditText) findViewById(R.id.editText_titulo_id);
        mResume = (EditText) findViewById(R.id.editText_tarefa_id);

        mTaskRepository = new TaskRepository(getApplicationContext());
//        Intent intent = getIntent();
//        task = intent.getParcelableExtra("task");
//
//
//        if (task != null){
//            mTitle.setText(task.getTitle());
//            mResume.setText(task.getResume());
//        }

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
                    Toast.makeText(CriarActivity.this, "Atenção. Todos os campos procisam ser preenchidos", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        Task TmpTask = new Task(mTitle.getText().toString(), mResume.getText().toString());
                        mTaskRepository.Save(TmpTask);
                        CleanEditText();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    public void CleanEditText() {
        Toast.makeText(CriarActivity.this, "Tarefa gravada com sucesso", Toast.LENGTH_SHORT).show();
        mTitle.setText("");
        mResume.setText("");
    }
}
