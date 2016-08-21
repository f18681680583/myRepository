package cloudnote.util;

import java.io.Serializable;

public class NoteResult implements Serializable{
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", obj=" + obj + "]";
	}
	
	public int getCodestatus() {
		return codestatus;
	}
	public void setCodestatus(int codestatus) {
		this.codestatus = codestatus;
	}

	private int codestatus=1;
	private int status;//0表示成功，其他失败
	private String msg;
	private Object obj;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
