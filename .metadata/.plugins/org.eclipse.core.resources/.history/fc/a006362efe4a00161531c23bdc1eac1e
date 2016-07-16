package br.edu.ifba.curso.webservice.mongo.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReturnDocument;

import br.edu.ifba.curso.webservice.mongo.Twitte;
import br.edu.ifba.curso.webservice.mongo.Usuario;

public class FachadaBD {
	private static final String HOST = "localhost";
	private static final int PORT =  27017;
	private static final String DB_NAME = "twitter-mongo";
	
	private MongoClient client;
	private MongoDatabase db;
	
	private static FachadaBD instancia = null;
	
	public static FachadaBD getInstancia() {
		if (instancia == null) {
			instancia = new FachadaBD();
		}
		return instancia;
	}
	
	private FachadaBD() {}
	
	public MongoDatabase getDB() {
		client = new MongoClient(HOST, PORT);
		db = client.getDatabase(DB_NAME);
		return db;
	}
	
	public MongoCollection getColecao(String colecao) {
		MongoCollection col = FachadaBD.getInstancia().
				getDB().getCollection(colecao);
		return col;
	}
	
	// INSERT 
	public void insertOne(Usuario novo) {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia().
				getColecao("usuario");
		
		Document documento = new Document();
		documento.append("nome", novo.getNome());
		documento.append("userName", novo.getUserName());
		documento.append("email", novo.getEmail());
		documento.append("senha", novo.getSenha());
		
		colecaoUsuario.insertOne(documento);
	}
	
	// FIND
	public List<Usuario> find() {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia().
				getColecao("usuario");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		FindIterable<Document> i = colecaoUsuario.find();
		
		i.forEach(new Block<Document>() {

			@Override
			public void apply(Document documento) {
				Usuario usuario = new Usuario();
				
				usuario.setId(documento.get("_id").toString());
				usuario.setNome(documento.getString("nome"));
				usuario.setUserName(documento.getString("userName"));
				usuario.setEmail(documento.getString("email"));
				usuario.setSenha(documento.getString("senha"));
				
				usuarios.add(usuario);
			}
			
		});
		return usuarios;
	}
	
	// FIND-ONE
	public Usuario findOne(String userName) {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia().
				getColecao("usuario");
		Usuario usuario = new Usuario();
		FindIterable<Document> i = colecaoUsuario.find(new Document("userName", userName));
		
		i.forEach(new Block<Document>() {

			@Override
			public void apply(Document documento) {
				usuario.setId(documento.get("_id").toString());
				usuario.setNome(documento.getString("nome"));
				usuario.setUserName(documento.getString("userName"));
				usuario.setEmail(documento.getString("email"));
				usuario.setSenha(documento.getString("senha"));
			}
			
		});
		return usuario;
	}
	
	public void update(String userName, Usuario user) {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia().
				getColecao("usuario");
		
		Usuario usuario = FachadaBD.getInstancia().findOne(userName);
		
		Document documento = new Document();
		documento.append("nome", user.getNome());
		documento.append("userName", user.getUserName());
		documento.append("email", user.getEmail());
		documento.append("senha", user.getSenha());
		
		colecaoUsuario.updateOne(new Document("userName", usuario.getUserName()), new Document("$set", documento));
	}
	
	public void delete(String userName) {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia()
				.getColecao("usuario");
		
		colecaoUsuario.deleteOne(new Document("userName", userName));
		
	}
	
	public void insertOne(String userName, Twitte twitte) {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia()
				.getColecao("usuario");
		MongoCollection colecaoTwitte = FachadaBD.getInstancia()
				.getColecao("twitte");
		Usuario usuario = FachadaBD.getInstancia().findOne(userName);
		Document documento = new Document();
		documento.append("idUser", usuario.getId());
		documento.append("conteudo", twitte.getConteudo());
		colecaoTwitte.insertOne(documento);
	}
	
	public List<Twitte> buscarTwittes(String userName) {
		MongoCollection colecaoUsuario = FachadaBD.getInstancia()
				.getColecao("usuario");
		MongoCollection colecaoTwitte = FachadaBD.getInstancia()
				.getColecao("twitte");
		
		Usuario usuario = FachadaBD.getInstancia().findOne(userName);
		Document documento = new Document();
		
		List<Twitte> twittes = new ArrayList<Twitte>();
		FindIterable<Document> i = colecaoTwitte.find(new Document("idUser", usuario.getId()));
		
		i.forEach(new Block<Document>() {

			@Override
			public void apply(Document documento) {
				Twitte twitte = new Twitte();
				
				twitte.setId(documento.get("_id").toString());
				twitte.setIdUser(documento.getString("idUser"));
				twitte.setConteudo(documento.getString("conteudo"));
				
				twittes.add(twitte);
			}
		});
		return twittes;
	}
}
