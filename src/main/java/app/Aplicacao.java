package app;

import java.util.List;

import dao.DAO;
import dao.ProfessoroDAO;
import model.Professor;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		ProfessoroDAO usuarioDAO = new ProfessoroDAO();
		
		System.out.println("\n\n==== Inserir usuário === ");
		Professor usuario = new Professor(11, "pablo", "pablo",'M');
		if(usuarioDAO.insert(usuario) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "pablo"));
			
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<Professor> usuarios = usuarioDAO.getSexoMasculino();
		for (Professor u: usuarios) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar senha (código (" + usuario.getCodigo() + ") === ");
		usuario.setSenha(DAO.toMD5("pablo"));
		usuarioDAO.update(usuario);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		usuarios = usuarioDAO.getOrderByCodigo();
		for (Professor u: usuarios) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + usuario.getCodigo() + ") === ");
		usuarioDAO.delete(usuario.getCodigo());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		usuarios = usuarioDAO.getOrderByLogin();
		for (Professor u: usuarios) {
			System.out.println(u.toString());
		}
	}
}