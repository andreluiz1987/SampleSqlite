package detectordocumento.mercantildobrasil.com.br.samplesqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import detectordocumento.mercantildobrasil.com.br.samplesqlite.db.DatabaseHelper;
import detectordocumento.mercantildobrasil.com.br.samplesqlite.model.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Button btnAdd;

    private List<User> userList = new ArrayList<>();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        btnAdd = findViewById(R.id.add);

        db = new DatabaseHelper(this);

        userList = db.getAllUsers();

        userAdapter = new UserAdapter(this, userList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(userAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long id = db.insertUser(UUID.randomUUID().toString().replace("-", ""));

                User n = db.getUser(id);

                if (n != null) {
                    // adding new note to array list at 0 position
                    userList.add(0, n);

                    // refreshing the list
                    userAdapter.notifyDataSetChanged();
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Usuario selecionado: " + userList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Usuario pressionado: " + userList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
