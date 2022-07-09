package test;

import database.persistence.CategoriasDAO;
import database.persistence.LetrasDAO;
import database.persistence.ParticipanteDAO;
import rule.Categorias;
import rule.Participante;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        deleteCategory();
    }

    private static void testInsert() {
        Participante participante = new Participante();
        participante.setNome("João");
        participante.setPontuacao(0);
        ParticipanteDAO.inserir(participante);

        Categorias categorias = new Categorias();
        categorias.setParticipante(participante);
        categorias.setPessoa("João");
        categorias.setCarro("Fusca");
        categorias.setCidadeEstadoPais("São Paulo");
        categorias.setFruta("Banana");
        categorias.setObjeto("Cadeira");
        categorias.setAnimal("Cachorro");
        categorias.setLetraSorteada(1);

        CategoriasDAO categoriasDAO = new CategoriasDAO();

        categoriasDAO.insert(categorias);
    }
    private static void getLetters() {
        System.out.println((LetrasDAO.getLetras()));
    }
    private static void deleteCategory() {
        CategoriasDAO.removeAll();
    }
}

