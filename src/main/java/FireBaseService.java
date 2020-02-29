/*
 * import java.util.concurrent.ExecutionException;
 * 
 * import org.springframework.stereotype.Service;
 * 
 * import com.bridgelabz.demo.model.User; import com.google.api.core.ApiFuture;
 * import com.google.cloud.firestore.Firestore; import
 * com.google.cloud.firestore.WriteResult; import
 * com.google.firebase.cloud.FirestoreClient;
 * 
 * @Service public class FireBaseService { public String saveUserDetails(User
 * user) throws InterruptedException, ExecutionException {
 * 
 * Firestore dbFirestore = FirestoreClient.getFirestore();
 * ApiFuture<WriteResult> collectionsapiApiFuture = (ApiFuture<WriteResult>)
 * dbFirestore.collection("user") .document(user.getName()); return
 * collectionsapiApiFuture.get().getUpdateTime().toString(); } }
 */