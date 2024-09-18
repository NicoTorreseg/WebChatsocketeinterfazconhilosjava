package Cod_Mensajes;

public class MensajeCod {
private int TipodeMensaje;
private Object Mensaje;
public Object getMensaje() {
	return Mensaje;
}
public void setMensaje(Object mensaje) {
	Mensaje = mensaje;
}
public int getTipodeMensaje() {
	return TipodeMensaje;
}
public void setTipodeMensaje(int tipodeMensaje) {
	TipodeMensaje = tipodeMensaje;
}
}
