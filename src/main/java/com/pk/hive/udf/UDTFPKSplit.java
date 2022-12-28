//package com.pk.hive.udf;
//
//import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
//import org.apache.hadoop.hive.ql.metadata.HiveException;
//import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
//import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
//import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
//import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
//import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
//import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UDTFPKSplit extends GenericUDTF {
//
//    private List<String> outs = new ArrayList<>();
//
//    @Override
//    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
//
//        List<String> fieldNames = new ArrayList<>();
//        List<ObjectInspector> fieldOIs = new ArrayList<>();
//
//        fieldNames.add("word");
//        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
//
//        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,
//                fieldOIs);
//    }
//
//    @Override
//    public void process(Object[] args) throws HiveException {
//        String input = args[0].toString();
//        String splitParam = args[1].toString();
//
//        String[] fields = input.split(splitParam);
//        for(String field : fields) {
//            outs.clear();
//            outs.add(field);
//            forward(outs);
//        }
//    }
//
//    @Override
//    public void close() throws HiveException {
//
//    }
//}
