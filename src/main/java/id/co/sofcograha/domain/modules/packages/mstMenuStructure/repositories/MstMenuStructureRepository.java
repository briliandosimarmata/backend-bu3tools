package id.co.sofcograha.domain.modules.packages.mstMenuStructure.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import id.co.sofcograha.domain.modules.constants.Constans;
import id.co.sofcograha.domain.modules.enums.ModulType;
import id.co.sofcograha.domain.modules.extendables.MenuStructureDto;
import id.co.sofcograha.domain.modules.packages.mstMenuStructure.entities.MstMenuStructure;
import id.co.sofcograha.domain.modules.packages.mstMenuStructure.entities.MstMenuStructureId;

@Repository
public class MstMenuStructureRepository extends SimpleJpaRepository<MstMenuStructure, MstMenuStructureId> {

	@Autowired
	private EntityManager em;

	public MstMenuStructureRepository(EntityManager em) {
		super(MstMenuStructure.class, em);
	}

	@SuppressWarnings("unchecked")
	public List<MenuStructureDto> findAllChildMenuWithSettings(String sessionId) {
		try {
			Query query = em.createNativeQuery(
					"select gen_random_uuid() as id, a.menu_id, a.menu_sequence, \n"
							+ "		a.menu_desc, a.modul_id, c.icon_class, c.routing_path \n"
							+ "	from mst_menu_structure a \n" + "				inner join mst_modul b \n"
							+ "						on b.modul_id = a.modul_id \n" + "				left join (\n"
							+ "								select menu_id, menu_sequence, icon_class, routing_path \n"
							+ "									from sg_temp_menu_structure_settings \n"
							+ "									where session_id = :sessionId \n"
							+ "						  ) c \n" + "						on c.menu_id = a.menu_id \n"
							+ "						and c.menu_sequence = a.menu_sequence \n"
							+ "	where a.menu_id like :menuIdMenuUtama \n"
							+ "	and b.modul_id not in (:modulIdMenuUtama, :modulIdMenuMaster) \n"
							+ "	and b.modul_type = :modulType \n" + "	order by concat(a.menu_id, a.menu_sequence)",
					MenuStructureDto.class);
			query.setParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA.concat("%"));
			query.setParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
			query.setParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);
			query.setParameter("modulType", ModulType.P.name());
			query.setParameter("sessionId", sessionId);

			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MenuStructureDto> findAllMenuWithSettings(String sessionId) {
		try {
			Query query = em.createNativeQuery("select gen_random_uuid() as id, a.menu_id, \n"
					+ "		a.menu_sequence, a.menu_desc, a.modul_id, \n" + "		b.icon_class, b.routing_path \n"
					+ "		from mst_menu_structure a \n" + "				left join (\n"
					+ "								select menu_id, menu_sequence, icon_class, routing_path \n"
					+ "									from sg_temp_menu_structure_settings \n"
					+ "									where session_id = :sessionId \n"
					+ "						  ) b \n" + "						on b.menu_id = a.menu_id \n"
					+ "						and b.menu_sequence = a.menu_sequence \n"
					+ "		where a.menu_id like :menuIdMenuUtama \n"
					+ "			and a.modul_id not in(:modulIdMenuUtama, :modulIdMenuMaster) \n"
					+ "	    order by concat(a.menu_id, a.menu_sequence)", MenuStructureDto.class);
			query.setParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA.concat("%"));
			query.setParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
			query.setParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);
			query.setParameter("sessionId", sessionId);

			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MenuStructureDto> findAllParentMenuWithSettings(String sessionId) {
		try {
			Query query = em.createNativeQuery(
					"select gen_random_uuid() as id, a.menu_id, a.menu_sequence, \n"
							+ "		a.menu_desc, a.modul_id, c.icon_class, c.routing_path \n"
							+ "	from mst_menu_structure a \n" + "				inner join mst_modul b \n"
							+ "						on b.modul_id = a.modul_id \n" + "				left join (\n"
							+ "								select menu_id, menu_sequence, icon_class, routing_path \n"
							+ "									from sg_temp_menu_structure_settings \n"
							+ "									where session_id = :sessionId \n"
							+ "						  ) c \n" + "						on c.menu_id = a.menu_id \n"
							+ "						and c.menu_sequence = a.menu_sequence \n"
							+ "	where a.menu_id = :menuIdMenuUtama \n"
							+ "	and b.modul_id not in (:modulIdMenuUtama, :modulIdMenuMaster) \n"
							+ "	and b.modul_type = :modulType \n" + "	order by concat(a.menu_id, a.menu_sequence)",
					MenuStructureDto.class);
			query.setParameter("menuIdMenuUtama", Constans.MENU_ID_MENU_UTAMA);
			query.setParameter("modulIdMenuUtama", Constans.MODUL_ID_MENU_UTAMA);
			query.setParameter("modulIdMenuMaster", Constans.MODUL_ID_MENU_MASTER);
			query.setParameter("modulType", ModulType.M.name());
			query.setParameter("sessionId", sessionId);

			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

}
