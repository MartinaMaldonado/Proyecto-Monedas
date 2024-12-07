package controller.ejercicios;

import model.Moneda;

public class MonedaController {
    public static Moneda getMoneda(int num) {
        switch (num) {
			case 1:
    			return new Moneda(34.30002219037716, "THB", "Baht tailandés", "฿", "Tailandia");
			case 2:
    			return new Moneda(15.33177946372839, "GHS", "Cedi ghanés", "₵", "Ghana");
			case 3:
    			return new Moneda(129.51699529673766, "KES", "Chelín keniano", "KSh", "Kenia");
			case 4:
    			return new Moneda(7.054268626858402, "DKK", "Corona danesa", "kr", "Dinamarca");
			case 5:
    			return new Moneda(11.048001639216286, "NOK", "Corona noruega", "kr", "Noruega");
			case 6:
    			return new Moneda(10.903668408820698, "SEK", "Corona sueca", "kr", "Suecia");
			case 7:
    			return new Moneda(1308.9907010165364, "IQD", "Dinar iraquí", "ع.د", "Irak");
			case 8:
    			return new Moneda(0.7090693293053073, "JOD", "Dinar jordano", "د.أ", "Jordania");
			case 9:
    			return new Moneda(0.3074133100738776, "KWD", "Dinar kuwaití", "د.ك", "Kuwait");
			case 10:
    			return new Moneda(3.6728609345379604, "AED", "Dirham de los EAU", "د.إ", "Emiratos Árabes Unidos");
			case 11:
    			return new Moneda(1.5349086190345655, "AUD", "Dólar australiano", "A$", "Australia");
			case 12:
    			return new Moneda(0.9987712540592981, "BSD", "Dólar bahame", "B$", "Bahamas");
        }
    }
    public static void mostrarMonedas(){
        for(int i=1; i<=50; i++){
            Moneda moneda = MonedaController.getMoneda(i);
            System.out.println(i+". "+moneda.nombre+" ("+ moneda.codigo_iso+") ("+ moneda.simbolo+") de "+ moneda.pais_o_region+".");
        }
    }
}
