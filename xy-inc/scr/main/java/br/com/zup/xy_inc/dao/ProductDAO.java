package br.com.zup.xy_inc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import br.com.zup.xy_inc.model.Product;
import br.com.zup.xy_inc.util.HibernateUtil;

/**
 * Classe DAO que será utilizada para fazer os acessos ao banco.
 * @author luizhenriquesantana
 *
 */
@Component
public class ProductDAO {

	/**
	 * Insere um produto no banco.
	 * @param entidade
	 * @throws Exception
	 */
	public void create(Product entidade) throws Exception {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(entidade);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error ao criar o produto");
		} finally {
			session.flush();
			session.close();
		}
	}

	/**
	 * Deleta um registro do banco
	 * @param entidadeId
	 * @param classeEntidade
	 * @throws Exception
	 */
	public Boolean delete(Long entidadeId) throws Exception {
		Transaction trns = null;
		
		Boolean isRetornoOk = Boolean.FALSE;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Product entidade = (Product) session.load(Product.class, new Long(entidadeId));

			session.delete(entidade);
			session.getTransaction().commit();
			isRetornoOk = Boolean.TRUE;
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error ao excluir o produto");
		} finally {
			session.flush();
			session.close();
		}
		
		return isRetornoOk;
	}

	/**
	 * Lista todos os registros
	 * @param entidade
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> list() {
		List<Product> list = new ArrayList<Product>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			list = session.createQuery("select id, name, description, price, category from Product").list();

		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		
		
		return list;
	}
	
	/**
	 * Recupera um registro por id
	 * @param objId
	 * @return
	 */
	public Product getObj(Long objId) {

		Product obj = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			obj = (Product) session.get(Product.class, new Long(objId));
		} finally {
			session.flush();
			session.close();
		}

		return obj;
	}

	/**
	 * Atualiza um registro
	 * @param product
	 * @throws Exception
	 */
	public Boolean update(Product product) throws Exception {
		
		Boolean isRetornoOk = Boolean.FALSE;
		
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(product);
			session.getTransaction().commit();
			
			isRetornoOk = Boolean.TRUE;
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			throw new Exception("Error ao atualizar o produto.");
		} finally {
			session.flush();
			session.close();
		}
		
		return isRetornoOk;
	}
}
