package com.example.listacompras;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listacompras.model.Item;
import com.example.listacompras.model.ListaDeCompras;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountFragment extends Fragment {

    private EditText mEditTextEmail;
    private EditText mEditTextPassWord;
    private TextView mTextViewCreateNewAccount;
    private Button mButtonLogin;
    private FirebaseAuth mAuth;
    private View view;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        loadViews();
        clickButtons();

        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    private void updateUI() {
        if(mAuth.getCurrentUser() != null){
            Navigation.findNavController(view).navigate(R.id.action_createAccountFragment_to_nav_home);
        }else{
            Toast.makeText(getContext(),"Não foi possivel logar", Toast.LENGTH_SHORT).show();
        }
    }


    private void clickButtons() {

        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassWord.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    List<Item> itens = new ArrayList<>();
                                    itens.add(new Item("Café"));
                                    itens.add(new Item("Açucar"));

                                    FirebaseFirestore.getInstance()
                                            .collection(mAuth.getCurrentUser().getUid())
                                            .add(new ListaDeCompras("Lista 1",itens));
                                    

                                    updateUI();
                                } else {
                                    Toast.makeText(getContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI();
                                }
                            }
                        });
            }
        });
    }

    private void loadViews() {
        mEditTextEmail =  view.findViewById(R.id.editext_email);
        mEditTextPassWord =  view.findViewById(R.id.editext_password);
        mTextViewCreateNewAccount =  view.findViewById(R.id.textview_criarconta);

        mTextViewCreateNewAccount.setEnabled(false);
        mTextViewCreateNewAccount.setVisibility(View.INVISIBLE);

        mButtonLogin =  view.findViewById(R.id.button_logar);
    }
}