package nz.co.logicons.tlp.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.co.logicons.tlp.api.service.SampleMainService;
import nz.co.logicons.tlp.api.service.SampleOneService;
import nz.co.logicons.tlp.api.service.SampleTwoService;
import nz.co.logicons.tlp.core.mongo.MongoDatastore;
import nz.co.logicons.tlp.core.mongo.TransformOperation;

@Service
public class SampleMainServiceImpl
    implements
    SampleMainService
{

  @Autowired
  private MongoDatastore datastore;

  @Autowired
  private TransformOperation transformOperation;

  @Autowired
  private SampleOneService oneService;

  @Autowired
  private SampleTwoService twoService;

  @Override
  public String executeMain()
  {
    return datastore.getDocumentInStr("TN_Job", "2", false);
  }

}
