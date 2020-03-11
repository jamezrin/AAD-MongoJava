package me.jamezrin.dam.mongojava;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import com.mongodb.AutoEncryptionSettings;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) {
		MongoClient mongoClient = MongoClients.create("mongodb://root:toor@127.0.0.1");
		MongoDatabase database = mongoClient.getDatabase("EjerciciosMongo");
		MongoCollection<Document> collection = database.getCollection("inventario");
		
		// Limpiar todo
		collection.drop();
		
		System.out.println("=== Ejercicio 3");
		ej3(collection);
		
		System.out.println("=== Ejercicio 4.a");
		ej4a(collection);
		
		System.out.println("=== Ejercicio 4.b");
		ej4b(collection);
		
		System.out.println("=== Ejercicio 4.c");
		ej4c(collection);
		
		System.out.println("=== Ejercicio 4.d");
		ej4d(collection);
		
		System.out.println("=== Ejercicio 4.e");
		ej4e(collection);
		
		System.out.println("=== Ejercicio 4.f");
		ej4f(collection);
		
		System.out.println("=== Ejercicio 4.g");
		ej4g(collection);
		
		System.out.println("=== Ejercicio 4.h");
		ej4h(collection);
		
		System.out.println("=== Ejercicio 5.a");
		ej5a(collection);
		
		System.out.println("=== Ejercicio 5.b");
		ej5b(collection);
		
		System.out.println("=== Ejercicio 5.c");
		ej5c(collection);
		
		System.out.println("=== Ejercicio 5.d");
		ej5d(collection);
		
		System.out.println("=== Ejercicio 6.a");
		ej6a(collection);
		
		System.out.println("=== Ejercicio 6.b");
		ej6b(collection);
		
		System.out.println("=== Ejercicio 6.c");
		ej6c(collection);
		
		System.out.println("=== Ejercicio 6.d");
		ej6d(collection);
		
		System.out.println("=== Ejercicio 6.e");
		ej6e(collection);
		
		System.out.println("=== Ejercicio 6.f");
		ej6f(collection);
		
		System.out.println("=== Ejercicio 6.g");
		ej6g(collection);
		
		System.out.println("=== Ejercicio 6.h");
		ej6h(collection);
		
		System.out.println("=== Ejercicio 7.a");
		ej7a(collection);
		
		System.out.println("=== Ejercicio 7.b");
		ej7b(collection);
	}
	
	public static void ej3(MongoCollection<Document> coll) {
		Document art1 = new Document()
				.append("articulo", "ABC1")
				.append("detalles", new Document()
						.append("modelo", "14Q3")
						.append("proveedor", "XYZ Company"))
				.append("stock", Arrays.asList(
						new Document()
							.append("talla", "S")
							.append("cantidad", 25),
						new Document()
							.append("talla", "M")
							.append("cantidad", 50)
				))
				.append("categoria", "ropa");
		
		Document art2 = new Document()
				.append("articulo", "ABC2")
				.append("detalles", new Document()
						.append("modelo", "14Q3")
						.append("proveedor", "M1 Corporation"))
				.append("stock", Arrays.asList(
						new Document()
							.append("talla", "M")
							.append("cantidad", 50)
				))
				.append("categoria", "ropa");
		
		Document art3 = new Document()
				.append("articulo", "MNO2")
				.append("detalles", new Document()
						.append("modelo", "14Q3")
						.append("proveedor", "ABC Company"))
				.append("stock", Arrays.asList(
						new Document()
							.append("talla", "S")
							.append("cantidad", 5),
						new Document()
							.append("talla", "M")
							.append("cantidad", 5),
						new Document()
							.append("talla", "M")
							.append("cantidad", 1)
				))
				.append("categoria", "ropa");
		
		Document art4 = new Document()
				.append("articulo", "IJK2")
				.append("detalles", new Document()
						.append("modelo", "14Q2")
						.append("proveedor", "M5 Corporation"))
				.append("stock", Arrays.asList(
						new Document()
							.append("talla", "S")
							.append("cantidad", 5),
						new Document()
							.append("talla", "L")
							.append("cantidad", 1)
				))
				.append("categoria", "hogar");
		
		coll.insertOne(art1);
		coll.insertOne(art2);
		coll.insertOne(art3);
		coll.insertOne(art4);
	}
	
	public static void ej4a(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find();
		it.forEach(App::printJson);
	}

	public static void ej4b(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(
				eq("categoria", "ropa"));
		it.forEach(App::printJson);
	}

	public static void ej4c(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(and(
				eq("categoria", "ropa"), 
				eq("detalles.modelo", "14Q3")));
		it.forEach(App::printJson);
	}
	
	public static void ej4d(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(or(
				eq("categoria", "hogar"), 
				eq("categoria", "comida")));
		it.forEach(App::printJson);
	}
	
	public static void ej4e(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(or(
				gt("stock.cantidad", 10), 
				lte("stock.cantidad", 4)));
		it.forEach(App::printJson);
	}
	
	public static void ej4f(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(
				eq("detalles.proveedor", "M1 Corporation"));
		it.forEach(App::printJson);
	}
	
	public static void ej4g(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(
				eq("stock.talla", "L"));
		it.forEach(App::printJson);
	}
	
	public static void ej4h(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(and(
				eq("stock.talla", "L"),
				eq("stock.cantidad", 1)
		));
		it.forEach(App::printJson);
	}
	
	// https://stackoverflow.com/a/44894656/4673065
	public static void ej5a(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(and(
				eq("categoria", "ropa"),
				eq("detalles.modelo", "14Q3")
		)).projection(fields(
				include("categoria"),
				excludeId()
		));
		it.forEach(App::printJson);
	}
	
	public static void ej5b(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(or(
				eq("categoria", "hogar"),
				eq("categoria", "comida")
		)).projection(fields(
				include("categoria"),
				include("_id")
		));
		it.forEach(App::printJson);
	}
	
	public static void ej5c(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(or(
				gt("stock.cantidad", 10),
				lte("categoria", 4)
		)).projection(fields(
				exclude("stock")
		));
		it.forEach(App::printJson);
	}
	
	public static void ej5d(MongoCollection<Document> coll) {
		Iterable<Document> it = coll.find(
				eq("stock.talla", "L")
		).projection(
				slice("stock", 1)
		);
		it.forEach(App::printJson);
	}
	
	public static void ej6a(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización: %s\n", coll.updateMany(
				eq("articulo", "MNO2"),
				combine(set("detalles.modelo", "14Q4"),
						set("categoria", "hogar"))
		));
		
		ej4a(coll);
	}
	
	public static void ej6b(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización: %s\n", coll.updateMany(
				eq("categoria", "hogar"),
				set("categoria", "casa")
		));
		
		ej4a(coll);
	}
	
	public static void ej6c(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización: %s\n", coll.updateMany(
				eq("articulo", "IJK2"),
				set("stock", Arrays.asList(
						new Document()
							.append("talla", "M")
							.append("cantidad", 7)
				))
		));
		
		ej4a(coll);
	}
	
	public static void ej6d(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización 1: %s\n", coll.updateMany(
				and(
						eq("articulo", "ABC2"),
						eq("stock.talla", "M")),
				set("precio", 8.5)
		));
		
		System.out.printf("Actualización 2: %s\n", coll.updateMany(
				and(
						eq("articulo", "ABC2"),
						eq("stock.talla", "M")),
				mul("precio", 3)
		));
		
		ej4a(coll);
	}
	
	public static void ej6e(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización 1: %s\n", coll.updateMany(
				and(
						eq("articulo", "ABC2"),
						eq("stock.talla", "M")),
				set("precio", 8.5)
		));
		
		System.out.printf("Actualización 2: %s\n", coll.updateMany(
				and(
						eq("articulo", "ABC2"),
						eq("stock.talla", "M")),
				mul("precio", 3)
		));
		
		ej4a(coll);
	}
	
	public static void ej6f(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización: %s\n", coll.updateMany(
				eq("categoria", "ropa"),
				set("precio", 6)
		));
		
		ej4a(coll);
	}
	
	public static void ej6g(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización: %s\n", coll.updateMany(
				eq("categoria", "ropa"),
				min("precio", 3)
		));
		
		ej4a(coll);
	}
	
	public static void ej6h(MongoCollection<Document> coll) {
		ej4a(coll);
		
		System.out.printf("Actualización: %s\n", coll.updateMany(
				eq("articulo", "TBD2"),
				combine(
						set("detalles", new Document()
								.append("modelo", "14Q3")
								.append("proveedor", "IJK Co.")),
						set("categoria", "hogar")),
				new UpdateOptions().upsert(true)
		));
		
		ej4a(coll);
	}
	
	public static void ej7a(MongoCollection<Document> coll) {
		coll.deleteMany(eq("categoria", "hogar"));
		ej4a(coll);
	}
	
	public static void ej7b(MongoCollection<Document> coll) {
		coll.deleteMany(gt("precio", 1));
		ej4a(coll);
	}
	
	private static void printJson(Document doc) {
		System.out.println(doc.toJson());
	}
}
