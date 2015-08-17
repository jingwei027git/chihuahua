import java.util.Arrays;

import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class test {

	public static void main(String[] args) throws Exception {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER \n ROLE_USER > ROLE_GUEST \n ROLE_CCCC > ROLE_USER");
		
		GrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_CCCC");
		GrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_ADMIN2");
		System.out.println(roleHierarchy.getReachableGrantedAuthorities(Arrays.asList(authority1, authority2)));
	}

}
