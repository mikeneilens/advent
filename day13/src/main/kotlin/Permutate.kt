fun <S> permute(list:List <S>):List<List<S>>{
    if(list.size==1) return listOf(list)
    val perms=mutableListOf<List <S>>()
    val sub=list[0]
    for(perm in permute(list.drop(1)))
        for (i in 0..perm.size){
            val newPerm=perm.toMutableList()
            newPerm.add(i,sub)
            perms.add(newPerm)
        }
    return perms
}