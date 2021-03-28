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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author 배치실행개발팀
 * @since 2012. 07.25
 * @version 1.0
 * @see
 *  <pre>
 *      개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012. 07.25  배치실행개발팀     최초 생성
 *  </pre>
 */

public class EmployeeRowMapper implements RowMapper<Object> {

	// "id"를 나타내는 상수
	public static final String EMP_NO_COLUMN = "emp_no";

	// "name"를 나타내는 상수
	public static final String BIRTH_DATE_COLUMN = "birth_date";

	// "credit"를 나타내는 상수
	public static final String FIRST_NAME_COLUMN = "first_name";
	
	public static final String LAST_NAME_COLUMN = "last_name";
	
	public static final String GENDER_COLUMN = "gender";
	
	public static final String HIRE_DATE_COLUMN = "hire_date";

	/**
	 * CustomerCredit VO를 set
	 */
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();

		employee.setEmp_no(rs.getString(EMP_NO_COLUMN));
		employee.setBirth_date(rs.getString(BIRTH_DATE_COLUMN));
		employee.setFirst_name(rs.getString(FIRST_NAME_COLUMN));
		employee.setLast_name(rs.getString(LAST_NAME_COLUMN));
		employee.setGender(rs.getString(GENDER_COLUMN));
		employee.setHire_date(rs.getString(HIRE_DATE_COLUMN));

		return employee;
	}

}
