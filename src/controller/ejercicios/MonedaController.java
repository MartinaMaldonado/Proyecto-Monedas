package controller.ejercicios;

import model.Moneda;
//Organizar todas las monedas en un switch para acceder a ellas por el índice
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
				case 13:
    			return new Moneda(1.401233277390278, "CAD", "Dólar canadiense", "C$", "Canadá");
			case 14:
    			return new Moneda(2.0102051270583217, "BBD", "Dólar de Barbados", "Bds$", "Barbados");
			case 15:
    			return new Moneda(1, "USD", "Dólar estadounidense", "$", "Estados Unidos");
			case 16:
    			return new Moneda(7.781799320191836, "HKD", "Dólar hongkonés", "HK$", "Hong Kong");
			case 17:
    			return new Moneda(157.5226372001208, "JMD", "Dólar jamaiquino", "J$", "Jamaica");
			case 18:
    			return new Moneda(1.7064270202315044, "NZD", "Dólar neozelandés", "NZ$", "Nueva Zelanda");
			case 19:
    			return new Moneda(32.4619890586972, "TWD", "Dólar taiwanés", "NT$", "República de China (Taiwán)");
			case 20:
    			return new Moneda(6.7572341200310175, "TTD", "Dólar trinitense", "TT$", "Trinidad y Tobago");
			case 21:
    			return new Moneda(0.9456000550608222, "EUR", "Euro", "€", "Eurozona");
			case 22:
    			return new Moneda(390.3504235810765, "HUF", "Forinto húngaro", "Ft", "Hungría");
			case 23:
    			return new Moneda(0.8809425016436325, "CHF", "Franco suizo", "CHF", "Suiza");
			case 24:
    			return new Moneda(41.542443712445134, "UAH", "Hryvnia ucraniana", "₴", "Ucrania");
			default:
        }
    }
    //Recorrerá las 50 monedas y se las mostrará al usuario
    public static void mostrarMonedas(){
        for(int i=1; i<=50; i++){
            Moneda moneda = MonedaController.getMoneda(i);
            System.out.println(i+". "+moneda.nombre+" ("+ moneda.codigo_iso+") ("+ moneda.simbolo+") de "+ moneda.pais_o_region+".");
        }
    }
}
