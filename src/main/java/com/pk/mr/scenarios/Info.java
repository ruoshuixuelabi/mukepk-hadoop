package com.pk.mr.scenarios;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data

public class Info implements Writable {

    private int empno;
    private String ename;
    private int deptno;
    private String dname;
    private int flag;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(empno);
        out.writeUTF(ename);
        out.writeInt(deptno);
        out.writeUTF(dname);
        out.writeInt(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.empno = in.readInt();
        this.ename = in.readUTF();
        this.deptno = in.readInt();
        this.dname = in.readUTF();
        this.flag = in.readInt();
    }

    @Override
    public String toString() {
        return empno + "\t" + ename + "\t" + deptno + "\t" + dname;
    }
}