package Modelo.Objetuak;

public class MunEst {

	private int cod_est;
	private int cod_mun;
	public MunEst(int cod_est, int cod_mun) {
		super();
		this.cod_est = cod_est;
		this.cod_mun = cod_mun;
	}
	public MunEst() {
		super();
	}
	public int getCod_est() {
		return cod_est;
	}
	public void setCod_est(int cod_est) {
		this.cod_est = cod_est;
	}
	public int getCod_mun() {
		return cod_mun;
	}
	public void setCod_mun(int cod_mun) {
		this.cod_mun = cod_mun;
	}
	@Override
	public String toString() {
		return "MunEst [cod_est=" + cod_est + ", cod_mun=" + cod_mun + "]";
	}
	
	
}
