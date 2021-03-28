/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package egovframework.example.bat.domain.trade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import egovframework.rte.bat.core.item.database.EgovJdbcBatchItemWriter;
import egovframework.rte.bat.core.item.database.support.EgovItemPreparedStatementSetter;
import egovframework.rte.bat.core.reflection.EgovReflectionSupport;

/**
 * @author 배치실행개발팀
 * @since 2012. 07.25
 * @version 1.0
 * @param <T>
 * @see
 *  <pre>
 *      개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012. 07.25  배치실행개발팀     최초 생성
 *  </pre>
 */
public class EmployeesInsertWriter<T> extends EgovJdbcBatchItemWriter<Object> {
	
	@Autowired
	private MariaDAO mariaDao;
	
	private JdbcOperations jdbcTemplate;
	
	private EgovItemPreparedStatementSetter<T> itemPreparedStatementSetter;
	
	private String[] params = new String[0];
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesInsertWriter.class);
	
	private boolean usingParameters;
	
	private EgovReflectionSupport<T> reflector;
	
	@Override
	public void afterPropertiesSet() {
		Assert.notNull(jdbcTemplate,
				"A DataSource or a SimpleJdbcTemplate is required.");

		if (params.length !=
				0) {
			usingParameters = true;
		}

		Assert.notNull(
				itemPreparedStatementSetter,
				"Using SQL statement with '?' placeholders requires an EgovMethodMapItemPreparedStatementSetter");
		reflector = new EgovReflectionSupport<T>();

	}
	
	@Override
	public void write(List items) throws Exception {
		JdbcTemplate jdbcTemplate= mariaDao.getJdbcTemplate();
		for(Object row : items) {
			Employee employee = (Employee) row;
			String rs =  jdbcTemplate.queryForObject("SELECT MAX(EMP_NO)+1 FROM employees", String.class);
			
			jdbcTemplate.update("INSERT INTO employees VALUES(?,?,?,?,?,?)",
					new Object[] {rs,employee.getBirth_date(),employee.getFirst_name(),employee.getLast_name(),employee.getGender(),employee.getHire_date()});
		}
//		List<Object> rows = items;
//		jdbcTemplate.batchUpdate("INSERT INTO employees VALUES((select max(emp_no)+1 from employees ee),?,?,?,?,?)",
//				new BatchPreparedStatementSetter() {
//					
//					@Override
//					public void setValues(PreparedStatement ps, int i) throws SQLException {
//						ps.setString(1, ((Employee) items.get(i)).getBirth_date());
//						ps.setString(2, ((Employee) items.get(i)).getFirst_name());
//						ps.setString(3, ((Employee) items.get(i)).getLast_name());
//						ps.setString(4, ((Employee) items.get(i)).getGender());
//						ps.setString(5, ((Employee) items.get(i)).getHire_date());
//					}
//					
//					@Override
//					public int getBatchSize() {
//						// TODO Auto-generated method stub
//						return items.size();
//					}
//				});
	}
}
