package choym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import choym.model.LineUser;

@Service("LineService")
public class LineService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * check user's follow list
	 * @param id user id
	 * @return
	 */
	public List<Map<String, Object>> getFollow(String id) {
		return this.jdbcTemplate.queryForList("SELECT * FROM follow WHERE line_id = ? ORDER BY serial_id", new Object[] { id });
	}
	
	/**
	 * user follow someone's IG
	 * @param line_id line id
	 * @param ig_id IG id
	 * @param mediaCode latest media code
	 * @return
	 */
	@Transactional
	public int addFollow(String line_id, String ig_id, String mediaCode) {
		return this.jdbcTemplate.update("INSERT INTO follow(line_id, ig_id, media_code) VALUES(?, ?, ?)", new Object[]{line_id, ig_id, mediaCode});
	}

	/**
	 * user unfollow someone's IG
	 * @param line_id line id
	 * @param ig_id IG id
	 * @return
	 */
	@Transactional
	public int deleteFollow(String line_id, String ig_id) {
		return this.jdbcTemplate.update("DELETE FROM follow WHERE line_id = ? AND ig_id = ?", new Object[]{line_id, ig_id});
	}

	/**
	 * check if user has followed IG user
	 * @param line_id line id
	 * @param ig_id ig id
	 * @return
	 */
	public boolean hasFollowed(String line_id, String ig_id) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList("SELECT * FROM follow where line_id = ? and ig_id = ?", new Object[] { line_id, ig_id });
		return !list.isEmpty();
	}
	
	/**
	 * check if line user exists
	 * @param id line id
	 * @return
	 */
	public boolean userExist(String id) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList("SELECT * FROM line_user WHERE id = ?", new Object[] { id });
		return !list.isEmpty();
	}

	/**
	 * add line user
	 * @param id line id
	 * @return 
	 */
	@Transactional
	public int addUser(String id) {
		return this.jdbcTemplate.update("INSERT INTO line_user(id, active) values(?, ?)", new Object[]{id, true});
	}
	
	/**
	 * delete line user
	 * @param id line id
	 * @return
	 */
	@Transactional
	public int deleteUser(String id) {
		// delete user
		return this.jdbcTemplate.update("DELETE FROM line_user WHERE id = ?", new Object[]{id});
	}
	
	/**
	 * get user
	 * @param id line id
	 * @return
	 */
	public LineUser getUser(String id) {
		List<LineUser> users = this.jdbcTemplate.query(
				"SELECT * FROM line_user where id = ?", new Object[]{id}, new BeanPropertyRowMapper<LineUser>(LineUser.class));
		
		return users.isEmpty() ? null : users.get(0);
	}

	/**
	 * disable / enable bot reminder
	 * @param b
	 */
	@Transactional
	public int enableBOT(String id, boolean b) {
		return this.jdbcTemplate.update("UPDATE line_user SET active = ? WHERE id = ?", new Object[]{b, id});
	}
}
