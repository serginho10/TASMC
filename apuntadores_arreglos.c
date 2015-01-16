
#include <stdio.h>

int main(){
   int x[] = {10, 11, 12, 13, 14, 15, 16, 17 ,18, 19};
   int i;
   
   for (i=0; i<=9; i++){
      printf("\ni = %d  x[i] = %d  *(x+i) = %d", i, x[i], *(x + i));
      
      printf("   &x[i] = %X    x+i = %X", &x[i], (x+i));
   }
   
   fflush(stdin);
   getchar();
   
   return (0);   
}
