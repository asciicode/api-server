/**
 * 
 */
package nz.co.logicons.tlp.api.rest.command;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nz.co.logicons.tlp.core.genericmodels.nodes.DocumentNode;
import nz.co.logicons.tlp.core.genericmodels.schemas.DocumentSchema;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;
import nz.co.logicons.tlp.core.mongo.TransformOperation;
import nz.co.logicons.tlp.core.rest.model.DocumentNodePayloadModel;

/**
 * @author Allen
 *
 */
public class DocumentNodeAddAllCommand
    implements
    Callable<ResponseEntity<?>>
{
  private MongoDatastore datastore;

  private TransformOperation transformOperation;

  private DocumentNodePayloadModel documentNodePayloadModel;

  public DocumentNodeAddAllCommand(MongoDatastore datastore, TransformOperation transformOperation,
    DocumentNodePayloadModel documentNodePayloadModel)
  {
    this.datastore = datastore;
    this.transformOperation = transformOperation;
    this.documentNodePayloadModel = documentNodePayloadModel;
  }

  @Override
  public ResponseEntity<?> call()
    throws Exception
  {
    DocumentSchema docSchema = datastore.getSchema(documentNodePayloadModel.getSchemaid());
    List<DocumentNode> docNodes = transformOperation.createDocumentNodes(documentNodePayloadModel.getPayload(),
        docSchema);

    HttpHeaders httpHeaders = new HttpHeaders();
    return new ResponseEntity<>(datastore.addAllDocument(docNodes), httpHeaders, HttpStatus.CREATED);
  }

}
