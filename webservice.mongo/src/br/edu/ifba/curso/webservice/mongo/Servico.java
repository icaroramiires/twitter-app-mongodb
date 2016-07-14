package br.edu.ifba.curso.webservice.mongo;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifba.curso.webservice.mongo.db.FachadaBD;

@Path("servico")
public class Servico {
	@POST
	@Path("/usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public void cadastrarUsuario(
				@FormParam("nome") String nome,
				@FormParam("userName") String userName,
				@FormParam("email") String email, 
				@FormParam("senha") String senha ) {
		Usuario novo = new Usuario();
		novo.setNome(nome);
		novo.setUserName(userName);
		novo.setEmail(email);
		novo.setSenha(senha);
		
		
		FachadaBD.getInstancia().insertOne(novo);	
	}
	
	@GET
	@Path("/usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuarios() {
		return FachadaBD.getInstancia().find();
	}
	
	@GET
	@Path("/usuario/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario buscarUsuario(
				@PathParam("userName") String userName
			) {
		return FachadaBD.getInstancia().findOne(userName);
	}
	
	@PUT
	@Path("/usuario/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public void atualizarUsuario(
				@PathParam("userName") String userName,
				@FormParam("nome") String nome, 
				@FormParam("userName") String novoUserName, 
				@FormParam("email") String email, 
				@FormParam("senha") String senha) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setUserName(novoUserName);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		FachadaBD.getInstancia().update(userName, usuario);
	}
	
	@DELETE
	@Path("/usuario/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteUsuario(
			@PathParam("userName") String userName){
		FachadaBD.getInstancia().delete(userName);
	}
}