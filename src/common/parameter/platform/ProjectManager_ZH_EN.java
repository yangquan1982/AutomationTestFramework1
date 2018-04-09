package common.parameter.platform;

import common.constant.constparameter.ConstUrl;

public class ProjectManager_ZH_EN {
	
	//运营商设置
	public static String China_Region = get_China_Region() ;
	
	public static String get_China_Region(){
		if(ConstUrl.language.contains("CN"))
		{
			return "中国区" ;
		}else
		{
			return "China Region" ;
		}	
	}

	
	//运营商设置 中国安徽
	public static String China_Anhui = get_China_Anhui() ;
	
	public static String get_China_Anhui(){
		if(ConstUrl.language.contains("CN"))
		{
			return "中国安徽" ;
		}else
		{
			return "Anhui" ;
		}	
	}
	
	//运营商设置 中国安徽
	public static String AnHuiSuzhou_Mobile  = get_AnHuiSuzhou_Mobile() ;
		
	public static String get_AnHuiSuzhou_Mobile(){
		if(ConstUrl.language.contains("CN"))
		{
			return "宿州移动" ;
		}else
		{
			return "AnHuiSuzhou Mobile" ;
		}	
	}
	
	//新建项目
	public static String New_Project  = get_New_Project() ;
			
	public static String get_New_Project(){
		if(ConstUrl.language.contains("CN"))
		{
			return "新建项目" ;
		}else
		{
			return "New Project" ;
		}	
	}
	
	//提交
	public static String Submit  = get_Submit() ;
				
	public static String get_Submit(){
		if(ConstUrl.language.contains("CN"))
		{
			return "提交" ;
		}else
		{
			return "Submit" ;
		}	
	}
		
		
	//项目列表
	public static String Project_List  = get_Project_List() ;
				
	public static String get_Project_List(){
		if(ConstUrl.language.contains("CN"))
		{
			return "项目列表" ;
		}else
		{
			return "Project List" ;
		}	
	}
	
	//角色管理
	public static String Role_Management  = get_Role_Management() ;
				
	public static String get_Role_Management(){
		if(ConstUrl.language.contains("CN"))
		{
			return "角色管理" ;
		}else
		{
			return "Role Management" ;
		}	
	}  
	
	//角色管理
	public static String GSC_Interface  = get_GSCInterface() ;
					
	public static String get_GSCInterface(){
		if(ConstUrl.language.contains("CN"))
		{
			return "GSC接口人" ;
		}else
		{
			return "" ;
		}	
	}  
	
	//角色管理
	public static String Automatic_Analysis_of_DT_Data  = get_Automatic_Analysis_of_DT_Data() ;
					
	public static String get_Automatic_Analysis_of_DT_Data(){
		if(ConstUrl.language.contains("CN"))
		{
			return "DT数据自动分析" ;
		}else
		{
			return "Automatic Analysis of DT Data" ;
		}	
	}
	
	//角色管理
	public static String Neighboring_Cell_Check_and_Optimization  = get_Neighboring_Cell_Check_and_Optimization() ;
					
	public static String get_Neighboring_Cell_Check_and_Optimization(){
		if(ConstUrl.language.contains("CN"))
		{
			return "邻区核查与优化" ;
		}else
		{
			return "Neighboring Cell Check and Optimization" ;
		}	
	}
	//角色管理
	public static String New_Role = get_New_Role() ;
						
	public static String get_New_Role(){
		if(ConstUrl.language.contains("CN"))
		{
			return "新建角色" ;
		}else
		{
			return "New Role" ;
		}	
	}
	//角色管理-界面
	public static String Engineering_Optimization = get_Engineering_Optimization() ;
							
	public static String get_Engineering_Optimization(){
		if(ConstUrl.language.contains("CN"))
		{
			return "工程优化" ;
		}else
		{
			return "Engineering Optimization" ;
		}	
	}
	
	//角色管理-界面
	public static String Basic_Optimization = get_Basic_Optimization() ;
								
	public static String get_Basic_Optimization(){
		if(ConstUrl.language.contains("CN"))
		{
			return "基础优化" ;
		}else
		{
			return "Basic Optimization" ;
		}	
	}
	//角色管理-界面
	public static String All = get_All() ;
								
	public static String get_All(){
		if(ConstUrl.language.contains("CN"))
		{
			return "全部" ;
		}else
		{
			return "All" ;
		}	
	}	
	
	//角色管理-界面
	public static String Theme_Optimization = get_Theme_Optimization() ;
								
	public static String get_Theme_Optimization(){
		if(ConstUrl.language.contains("CN"))
		{
			return "专题优化" ;
		}else
		{
			return "Theme Optimization" ;
		}	
	}	
	
	//角色管理-界面
	public static String Kpi_Report  = get_Kpi_Report() ;
								
	public static String get_Kpi_Report(){
		if(ConstUrl.language.contains("CN"))
		{
			return "KPI周报" ;
		}else
		{
			return "Kpi Report" ;
		}	
	}	
	//角色管理-界面
	public static String role_save  = get_Save() ;
								
	public static String get_Save(){
		if(ConstUrl.language.contains("CN"))
		{
			return "保存" ;
		}else
		{
			return "Save" ;
		}	
	}	
	//角色管理-界面
	public static String role_cancel  = get_Cancel() ;
								
	public static String get_Cancel(){
		if(ConstUrl.language.contains("CN"))
		{
			return "取消" ;
		}else
		{
			return "Cancel" ;
		}	
	}	
	//角色管理-界面
	public static String Huawei_Employee_Permission = get_Huawei_Employee_Permission() ;
								
	public static String get_Huawei_Employee_Permission(){
		if(ConstUrl.language.contains("CN"))
		{
			return "华为人员权限" ;
		}else
		{
			return "Huawei Employee Permission" ;
		}	
	}	
	//角色管理-界面
	public static String Partner_Employee_Permission = get_Partner_Employee_Permission() ;
									
	public static String get_Partner_Employee_Permission(){
		if(ConstUrl.language.contains("CN"))
		{
			return "合作方人员权限" ;
		}else
		{
			return "Cooperation partner permission" ;
		}	
	}	
	//角色管理-界面
	public static String Add_User = get_Add_User() ;
								
	public static String get_Add_User(){
		if(ConstUrl.language.contains("CN"))
		{
			return "添加用户" ;
		}else
		{
			return "Add User" ;
		}	
	}
	//角色管理-界面
	public static String role = get_role() ;
								
	public static String get_role(){
		if(ConstUrl.language.contains("CN"))
		{
			return "角色" ;
		}else
		{
			return "Role" ;
		}	
	}
	//角色管理-界面
	public static String Delete_Role = get_Delete_Role() ;
								
	public static String get_Delete_Role(){
		if(ConstUrl.language.contains("CN"))
		{
			return "删除角色" ;
		}else
		{
			return "Delete Role" ;
		}	
	}
	//角色管理-界面
	public static String OK = get_OK() ;
								
	public static String get_OK(){
		if(ConstUrl.language.contains("CN"))
		{
			return "确定" ;
		}else
		{
			return "OK" ;
		}	
	}
	//角色管理-界面
	public static String Delete_User = get_Delete_User() ;
								
	public static String get_Delete_User(){
		if(ConstUrl.language.contains("CN"))
		{
			return "删除用户" ;
		}else
		{
			return "Delete User" ;
		}	
	}
	//角色管理-界面
	public static String Change_Project_Manager = get_Change_Project_Manager() ;
								
	public static String get_Change_Project_Manager(){
		if(ConstUrl.language.contains("CN"))
		{
			return "变更项目经理" ;
		}else
		{
			return "Change Project Manager" ;
		}	
	}
	//角色管理-界面
	public static String Change_Batch_Import = get_Batch_Import() ;
								
	public static String get_Batch_Import(){
		if(ConstUrl.language.contains("CN"))
		{
			return "批量导入" ;
		}else
		{
			return "Batch Import" ;
		}	
	}
	//角色管理-界面
	public static String Import = get_Import() ;
								
	public static String get_Import(){
		if(ConstUrl.language.contains("CN"))
		{
			return "导入" ;
		}else
		{
			return "Import" ;
		}	
	}
	
}
