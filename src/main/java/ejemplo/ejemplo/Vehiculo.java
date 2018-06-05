package ejemplo.ejemplo;

public class Vehiculo { //Falta por programar

	private int cilindraje;
	private String placa;
	private String propietario;
	private int tipoVehiculo;
	
	public Vehiculo(int cilindraje, String placa, String propietario, int tipoVehiculo) {
		this.cilindraje = cilindraje;
		this.placa = placa;
		this.propietario = propietario;
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public Vehiculo() {
		
	}
	
	public int getCilindraje() {
		return cilindraje;
	}
	
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getPropietario() {
		return propietario;
	}
	
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	
	public int getTipoVehiculo() {
		return tipoVehiculo;
	}
	
	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
}
