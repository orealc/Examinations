<template>
	<div>
		<el-menu style="width: 200px; min-height: calc(100vh - 50px)" :default-active="this.$route.path" router
			class="el-menu-vertical-demo">
			<el-sub-menu index="1">
				<template #title>题库模块</template>
				<el-menu-item index="/Pent">题库管理</el-menu-item>
				<el-menu-item index="/jiaofujihua">试题管理</el-menu-item>
			</el-sub-menu>
		</el-menu>
	</div>
</template>

<script>

</script>

<style>

</style>
