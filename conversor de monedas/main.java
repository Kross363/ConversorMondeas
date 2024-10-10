import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Conversiones> conversiones = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        //TODO ESTO AGREGADO DESDE LA LINEA 24 HASTA LA 32 ES PARA AGREGAR LA FECHA Y LA HORA AL JSON
        //CON EL DateTimeFormatter SE LE PUEDE AGREGAR EL FORMATO QUE DESEE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy, HH:mm", new Locale("es", "ES"));
        JsonSerializer<LocalDateTime> serializer = (src, typeOfSrc, context) ->
                src == null ? null : new com.google.gson.JsonPrimitive(src.format(formatter));
        //CON ESTA LINEA DE ABAJO EL ARCHIVO JSON PUEDE LEER LA FECHA EN EL FORMATO QUE LE DESIGNAMOS
        JsonDeserializer<LocalDateTime> deserializer = (json, typeOfT, context) ->{
            String fecha = json.getAsString();
            return LocalDateTime.parse(fecha, formatter);
        };
                //json == null ? null : LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, serializer)
                .registerTypeAdapter(LocalDateTime.class, deserializer)
                .setPrettyPrinting().
                create();
        String direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/",valor="";
        //https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/AMOUNT
        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json=response.body();
        System.out.println(json);
        Converter convertido =new Converter("","","");
        Conversiones conversiones_aux =new Conversiones(convertido,valor, LocalDateTime.now());
        String menu = """
                Sea bienvenido al conversor de monedas:
                                1) Dolar --> Peso argentino 
                                2) Peso argentino --> Dolar 
                                3) Dolar --> Real brasilero 
                                4) Real brasilero --> Dolar 
                                5) Dolar --> Peso colombiano 
                                6) Peso colombiano --> Dolar 
                                7) Salir
                *************************************                
                                
                """;
        String busqueda;
        System.out.println(menu);
        System.out.println("Ingrese el tipo de cambio que quiere hacer");
        busqueda = teclado.nextLine();
        while(true){
            if (busqueda.equals("1")) {
                //Dolar --> Peso argentino
                  System.out.println("Ingrese el valor que quiere hacer de dolar a peso argentino");
                  valor = teclado.nextLine();
                  direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/USD/ARS/" + valor;
                  request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                json = response.body();
                convertido = gson.fromJson(json, Converter.class);
                conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
                //System.out.println(convertido);
            }
            if (busqueda.equals("2")) {
                    //Peso argentino --> Dolar
                    System.out.println("Ingrese el valor que quiere hacer de peso argentino a dolar");
                    valor = teclado.nextLine();
                    direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/ARS/USD/" + valor;
                    request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json = response.body();
                    convertido = gson.fromJson(json, Converter.class);
                    conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
                    //System.out.println(convertido);
            }
            if(busqueda.equals("3")) {
                    //Dolar --> Real brasilero
                    System.out.println("Ingrese el valor que quiere hacer de dolar a real brasilero");
                    valor = teclado.nextLine();
                    direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/USD/BRL/" + valor;
                    request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json = response.body();
                    convertido = gson.fromJson(json, Converter.class);
                    conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
                    //System.out.println(convertido);
            }
            if(busqueda.equals("4")) {
                    //Real brasilero --> Dolar
                    System.out.println("Ingrese el valor que quiere hacer de real brasilero a dolar");
                    valor = teclado.nextLine();
                    direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/BRl/USD/" + valor;
                    request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json = response.body();
                    convertido = gson.fromJson(json, Converter.class);
                    conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//                    System.out.println(convertido);
            }
            if (busqueda.equals("5")) {
                    //Dolar --> Peso colombiano
                    System.out.println("Ingrese el valor que quiere hacer de dolar a peso colombiano");
                    valor=teclado.nextLine();
                    direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/USD/COP/"+valor;
                    request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json=response.body();
                    convertido = gson.fromJson(json, Converter.class);
                    conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//                    System.out.println(convertido);
            }
            if(busqueda.equals("6")){
                    //Peso colombiano --> Dolar
                    System.out.println("Ingrese el valor que quiere hacer de peso colombiano a dolar");
                    valor=teclado.nextLine();
                    direccion = "https://v6.exchangerate-api.com/v6/735a1bbd9847349ffdfc6e99/pair/COP/USD/"+valor;
                    request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json=response.body();
                    convertido = gson.fromJson(json, Converter.class);
                    conversiones.add(new Conversiones(convertido,valor, LocalDateTime.now()));
//                    System.out.println(convertido);
            }
            if (busqueda.equals("7")){
                break;
            }
            System.out.println("Ingrese el tipo de cambio que quiere hacer");
            System.out.println(menu);
            busqueda = teclado.nextLine();
        }
        System.out.println(conversiones);
        //CON ESTO SE LEE EL ARCHIVO DE HISTORIAL PARA GUARDAR LOS DATOS QUE NOS PROPORCIONA EL JSON
        FileReader lectura = new FileReader("historial.json");
        Type conversionListType = new TypeToken<List<Conversiones>>() {}.getType();
        List<Conversiones> lista_conversiones = gson.fromJson(lectura, conversionListType);
        conversiones.addAll(lista_conversiones);
        //CON ESTO SE ESCRIBE EL ARCHIVO DE HISTORIAL PARA ACTUALIZAR EL JSON
        FileWriter escritura=new FileWriter("historial.json");
        escritura.write(gson.toJson(conversiones)+"\n");
        escritura.close();
    }
}
