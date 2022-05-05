/**
 * 
 */
package nz.co.logicons.tlp.api.rest.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nz.co.logicons.tlp.api.rest.command.DocumentNodeAddAllCommand;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;
import nz.co.logicons.tlp.core.mongo.TransformOperation;
import nz.co.logicons.tlp.core.rest.model.DocumentNodePayloadModel;

/**
 * @author Allen
 *
 */
@RestController
@RequestMapping(API.VERSIONED_PATH + "/document-node")
public class DocumentNodeController
{
  @Autowired
  private MongoDatastore datastore;

  @Autowired
  private TransformOperation transformOperation;

  // @PreAuthorize("hasRole('ROLE_ADMIN')") doesn't work for role without prefix ROLE_
  @PreAuthorize("hasAuthority('SYSADMIN')")
  @PostMapping(consumes = "application/json", produces = "application/json")
  public Callable<ResponseEntity<?>> addAllDocumentNode(@RequestParam String schemaid,
    @RequestBody String payload)
  {
    return new DocumentNodeAddAllCommand(datastore, transformOperation, new DocumentNodePayloadModel(schemaid, payload));
  }
}
