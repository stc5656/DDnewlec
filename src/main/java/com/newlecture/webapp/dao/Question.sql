insert into memberRole(memberId, roleName) values('jojo','ROLE_ADMIN')

update Member set pwd='$2a$10$zpE1ThBwaRlZM2uMMShksurhrRjw/QtUZXB4cfON4.owLTFqkyQx.' from Member where id = 'jojo'


select * from Member
order by regDate desc
offset 0 rows
fetch next 5 rows only




select id, pwd password, 1 enable from Member where id = 'jojo'


select memberId, roleName authority from MemberRole where memberId = ?

select * from memberRole
update memberRole set defaultRole=1 from memberRole where memberId = 'jojo' 
