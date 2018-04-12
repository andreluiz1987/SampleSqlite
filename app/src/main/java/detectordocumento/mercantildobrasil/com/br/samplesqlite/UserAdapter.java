package detectordocumento.mercantildobrasil.com.br.samplesqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import detectordocumento.mercantildobrasil.com.br.samplesqlite.model.User;

/**
 * Created by andre.coelho on 12/04/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final Context context;
    private final List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflar

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        User user = userList.get(position);

        holder.id.setText(user.getId());
        holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView name;

        public UserViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_user);
            name = itemView.findViewById(R.id.name);
        }
    }
}
