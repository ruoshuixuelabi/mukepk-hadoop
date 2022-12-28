//package com.pk.hive.udf;
//
//import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
//import org.apache.hadoop.hive.ql.metadata.HiveException;
//import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
//import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUpper;
//import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
//import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
//
//public class UDFHelloNew  extends GenericUDFUpper {
//
//    /**
//     * 初始化操作
//     * @param arguments
//     * @return
//     * @throws UDFArgumentException
//     */
//    @Override
//    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
//
//        if(arguments.length != 1) {
//            throw new UDFArgumentException("参数个数只能为1");
//        }
//
//        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
//    }
//
//    /**
//     * 处理业务逻辑
//     * @param arguments
//     * @return
//     * @throws HiveException
//     */
//    @Override
//    public Object evaluate(DeferredObject[] arguments) throws HiveException {
//
//        // 如果arguments[0]为空呢？ 还需要添加判空处理
//        String input = arguments[0].get().toString();
//
//        return "Hello:" + input;
//    }
//
//    @Override
//    public String getDisplayString(String[] children) {
//        return null;
//    }
//}
