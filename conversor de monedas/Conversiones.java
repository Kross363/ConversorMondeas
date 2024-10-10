import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Conversiones {
    @SerializedName("base_code")
    private String base;
    @SerializedName("target_code")
    private String objetivo;
    @SerializedName("conversion_result")
    private String cambio;
    private String valor;
    private LocalDateTime fecha;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Conversiones(Converter convertido, String valor,LocalDateTime fecha) {
        this.base = convertido.base_code();
        this.objetivo = convertido.target_code();
        this.cambio = convertido.conversion_result();
        this.valor=valor+" "+convertido.base_code();
        this.fecha=fecha;
    }
//    public void moneda_convertir(String busqueda,List<Conversiones> conversiones) throws IOException, InterruptedException {
//        Scanner teclado = new Scanner(System.in);
//        //TODO ESTO AGREGADO DESDE LA LINEA 24 HASTA LA 32 ES PARA AGREGAR LA FECHA Y LA HORA AL JSON
//        //CON EL DateTimeFormatter SE LE PUEDE AGREGAR EL FORMATO QUE DESEE
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy, HH:mm", new Locale("es", "ES"));
//        JsonSerializer<LocalDateTime> serializer = (src, typeOfSrc, context) ->
//                src == null ? null : new com.google.gson.JsonPrimitive(src.format(formatter));
//        //CON ESTA LINEA DE ABAJO EL ARCHIVO JSON PUEDE LEER LA FECHA EN EL FORMATO QUE LE DESIGNAMOS
//        JsonDeserializer<LocalDateTime> deserializer = (json, typeOfT, context) ->{
//            String fecha = json.getAsString();
//            return LocalDateTime.parse(fecha, formatter);
//        };
//        //json == null ? null : LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, serializer)
//                .registerTypeAdapter(LocalDateTime.class, deserializer)
//                .setPrettyPrinting().
//                create();
//        String valor;
//        String direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair";
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(direccion))
//                .build();
//        HttpResponse<String> response = client
//                .send(request, HttpResponse.BodyHandlers.ofString());
//        String json = response.body();
//        if (busqueda.equals("1")) {
//            //Dolar --> Peso argentino
//            System.out.println("Ingrese el valor que quiere hacer de dolar a peso argentino");
//            valor = teclado.nextLine();
//            direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/USD/ARS/" + valor;
//            Converter convertido = gson.fromJson(json, Converter.class);
//            conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//            //System.out.println(convertido);
//        }
//        if (busqueda.equals("2")) {
//            //Peso argentino --> Dolar
//            System.out.println("Ingrese el valor que quiere hacer de dolar a peso argentino");
//            valor = teclado.nextLine();
//            direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/ARS/USD/" + valor;
//            Converter convertido = gson.fromJson(json, Converter.class);
//            conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//            //System.out.println(convertido);
//        }
//        if (busqueda.equals("3")) {
//            //Dolar --> Real brasilero
//            System.out.println("Ingrese el valor que quiere hacer de dolar a real brasilero");
//            valor = teclado.nextLine();
//            direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/USD/BRL/" + valor;
//            Converter convertido = gson.fromJson(json, Converter.class);
//            conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//            //System.out.println(convertido);
//        }
//        if (busqueda.equals("4")) {
//            //Real brasilero --> Dolarvalor;
//            System.out.println("Ingrese el valor que quiere hacer de real brasilero a dolar");
//            valor = teclado.nextLine();
//            direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/BRl/USD/" + valor;
//            Converter convertido = gson.fromJson(json, Converter.class);
//            conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//            //System.out.println(convertido);
//        }
//        if (busqueda.equals("5")) {
//            //Dolar --> Peso colombiano
//            System.out.println("Ingrese el valor que quiere hacer de dolar a peso colombiano");
//            valor = teclado.nextLine();
//            direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/USD/COP/" + valor;
//            Converter convertido = gson.fromJson(json, Converter.class);
//            conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//            //System.out.println(convertido);
//        }
//        if (busqueda.equals("6")) {
//            //Peso colombiano --> Dolar
//            System.out.println("Ingrese el valor que quiere hacer de peso colombiano a dolar");
//            valor = teclado.nextLine();
//            direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/COP/USD/" + valor;
//            Converter convertido = gson.fromJson(json, Converter.class);
//            conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//            //System.out.println(convertido);
//        }
//    }
    @Override
    public String toString() {
        return "Conversiones: " +
                "base=" + base  +
                ", objetivo=" + objetivo  +
                ", cambio=" + cambio ;
    }
}
