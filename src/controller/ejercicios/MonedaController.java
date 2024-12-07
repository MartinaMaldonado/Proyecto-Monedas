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
			case 25:
    			return new Moneda(25.267072553070914, "HNL", "Lempira hondure", "L", "Honduras");
			case 26:
    			return new Moneda(4.7079690207756535, "RON", "Leu rumano", "lei", "Rumania");
			case 27:
    			return new Moneda(49.579233782341966, "EGP", "Libra egipcia", "ج.م", "Egipto");
			case 28:
    			return new Moneda(0.7857220653977629, "GBP", "Libra esterlina", "£", "Reino Unido");
			case 29:
    			return new Moneda(1.702746463006217, "AZN", "Manat azerbaiyano", "₼", "Azerbaiyán");
			case 30:
    			return new Moneda(1682.2682677725834, "NGN", "Naira nigeriana", "₦", "Nigeria");
			case 31:
    			return new Moneda(1008.9859522223377, "ARS", "Peso argentino", "$", "Argentina");
			case 32:
    			return new Moneda(975.6195758510243, "CLP", "Peso chileno", "$", "Chile");
			case 33:
    			return new Moneda(4380.978573171703, "COP", "Peso colombiano", "$", "Colombia");
			case 34:
    			return new Moneda(20.384434245872804, "MXN", "Peso mexicano", "$", "México");
			case 35:
    			return new Moneda(7.7058285639393995, "GTQ", "Quetzal guatemalteco", "Q", "Guatemala");
			case 36:
    			return new Moneda(18.06075857035954, "ZAR", "Rand sudafricano", "R", "Sudáfrica");
			case 37:
    			return new Moneda(5.987499582089147, "BRL", "Real brasile", "R$", "Brasil");
			case 38:
    			return new Moneda(7.243635452579163, "CNY", "Renminbi", "¥", "China");
			case 39:
    			return new Moneda(4.443758985506794, "MYR", "Ringgit malayo", "RM", "Malasia");
			case 40:
    			return new Moneda(3.6388469254292146, "QAR", "Riyal catarí", "ر.ق", "Qatar");
			case 41:
    			return new Moneda(3.7546397226762047, "SAR", "Riyal saudí", "ر.س", "Arabia Saudí");
			case 42:
    			return new Moneda(106.77922797344225, "RUB", "Rublo ruso", "₽", "Rusia");
			case 43:
    			return new Moneda(84.56962855263106, "INR", "Rupia india", "₹", "India");
			case 44:
    			return new Moneda(15846.893587796256, "IDR", "Rupia indonesia", "Rp", "Indonesia");
			case 45:
    			return new Moneda(3.744990366847178, "PEN", "Sol peruano", "S/", "Perú");
			case 46:
    			return new Moneda(12831.048866022169, "UZS", "Som uzbeko", "soʻm", "Uzbekistán");
			case 47:
    			return new Moneda(515.3299857744445, "KZT", "Tenge kazajo", "₸", "Kazajistán");
			case 48:
    			return new Moneda(1395.5249683429793, "KRW", "Won surcoreano", "₩", "Corea del Sur");
			case 49:
    			return new Moneda(149.8603798219779, "JPY", "Yen japonés", "¥", "Japón");
			case 50:
    			return new Moneda(4.064294268926823, "PLN", "Zloty polaco", "zł", "Polonia");
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
