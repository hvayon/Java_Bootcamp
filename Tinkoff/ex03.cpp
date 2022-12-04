#include <iostream>

int NOD(int n1, int n2)
{
  int div;
  if (n1 == n2)
  	return n1;
  int d = n1 - n2;
  if (d < 0) {
    d = -d;  div = NOD(n1, d);
  } else
    div = NOD(n2, d); 
  return div;
}

int NOK(int n1, int n2) 
{ 
  return n1*n2 / NOD(n1, n2); 
}

int main(void) {
	int num;
	std::cin >> num;
	int i = 1;
	int a = 0;
	int b = 0;
	int min = 0;
	int min1 = 0;
	int min2 = 0;
	int res = 0;
	while (i != num)
	{
		a = i;
		b = num - i;
		res = NOK(a, b);
		if (i == 1) {
			min = res;
			min1 = a;
			min2 = b;
		}
		if (res < min) {
			min = res;
			min1 = a;
			min2 = b;
		}
		i++;
	}
	std::cout << min1 << ' ' << min2 << std::endl;
	return 0;
}
