package bean;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class TextoBean 
{
	private String id;
	private String valor;
	
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getValor() 
	{
		return valor;
	}
	public void setValor(String valor) 
	{
		this.valor = valor;
	}
	
	
	public void cambiar( )
	{
		String temp = id;
		id = valor;
		valor = temp;
	}
	
}
