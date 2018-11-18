package usonsonatemio.com.firebaseprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    CollectionReference referenceProducto;
    TextView txbProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txbProducto = findViewById(R.id.txtProducto);

        referenceProducto = FirebaseFirestore
                .getInstance()
                .collection("producto");


        referenceProducto.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                String informacion = "";
                //Lista de documentos cambiados
                List<DocumentChange> lisDocumentos = queryDocumentSnapshots.getDocumentChanges();

                //recorrerdocumentos cambiados
                for (DocumentChange doc: lisDocumentos){
                    informacion += "Precio: " + doc.getDocument().getString("precio")+"\n";
                    informacion += "Producto: " + doc.getDocument().getString("producto")+"\n";
                }

                txbProducto.setText(informacion);

            }
        });

    }
}
