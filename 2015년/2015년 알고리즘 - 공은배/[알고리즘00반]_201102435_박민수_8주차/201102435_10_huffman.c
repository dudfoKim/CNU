#include <stdio.h>
#include <stdlib.h>

typedef struct trecord{
	char alphabet;
	int* code;
	int freq;

	struct trecord* lchild;
	struct trecord* rchild;
}trecord;
typedef struct Table{
   char data;
   char huffmancode[100];
}Table;

void Huffman(trecord* minHeap);
void Build_Min_Heap(trecord* S);
void Min_heapify(trecord* S);
trecord* extract_max(trecord* S);
void insert(trecord* S, trecord x);
void MAKE_HUFFMAN_CODE(trecord* root, int* arr, int top);
int compare(const void *A, const void  *B);

int size = 27;
int k = 0;
Table* table;

void main() {
	FILE* fp;
	FILE* fop;
	trecord* tr = (trecord*)malloc(sizeof(trecord)*size);
	char* data = (char*)malloc(sizeof(char));
	char tmp;
	double min = 0;
	int cnt = 0;
	int i = 0;
	table = (Table*)malloc(sizeof(Table)*27);

	fp = fopen("week10.txt","rt");
	fop = fopen("00_201102435_huffman.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}

	// 파일에서 읽어서 구조체 배열에 저장
	while(fscanf(fp, "%c",&tmp) > 0){
		data = (char*)realloc(data, sizeof(char)*(cnt+1));
		data[cnt] = tmp;
		cnt++;
	}
	// 각 알파벳에 대한 trecord 배열 초기화. ' ',a-z
	for(i = 0; i< size; i++){
		if(i==0){
			tr[i].alphabet = ' ';
			tr[i].code = (int*)malloc(sizeof(int)*100);
			tr[i].freq = 0;
			tr[i].lchild = NULL;
			tr[i].rchild = NULL;
		}
		else {
			tr[i].alphabet = 96+i;
			tr[i].code = (int*)malloc(sizeof(int)*100);
			tr[i].freq = 0;
			tr[i].lchild = NULL;
			tr[i].rchild = NULL;
		}
	}
	// 빈도수 체크.
	for(i = 0; i < cnt; i++){
		if(data[i] == ' '){
			tr[0].freq+=1;
		}
		else{
			tr[data[i]-96].freq += 1;
		}
	}

	//MinHeap 생성.
	Build_Min_Heap(tr);
	//허프만 트리생성.
	Huffman(tr);
	//코드 생성.
	tr->code = (int*)malloc(sizeof(int)*100);
	MAKE_HUFFMAN_CODE(tr, tr->code, 0);
	//출력
	for(i = 0; i< cnt; i++){
		if(data[i] == ' '){
			printf("%s",table[0].huffmancode);
			fprintf(fop, "%s", table[0].huffmancode);
		}
		else{
			printf("%s",table[data[i]-96].huffmancode);
			fprintf(fop, "%s", table[data[i]-96].huffmancode);
		}
	}
	fclose(fp);
	fclose(fop);
}
void Huffman(trecord* minHeap) {
	int i = 0;
	
	while(1){
		trecord* tmp = (trecord*)malloc(sizeof(trecord));
		
		(*tmp).lchild = extract_max(minHeap);
		(*tmp).rchild = extract_max(minHeap);
		(*tmp).freq = (*tmp).lchild->freq+(*tmp).rchild->freq;
	
		insert(minHeap,(*tmp));
		if(size == 2){
			trecord* tmp2 = (trecord*)malloc(sizeof(trecord));
			(*tmp2).alphabet = (*minHeap).alphabet;
			(*tmp2).code = (*minHeap).code;
			(*tmp2).freq = (*minHeap).freq;
			(*tmp2).lchild = (*minHeap).lchild;
			(*tmp2).rchild = (*minHeap).rchild;
			
			
			minHeap =  (trecord*)realloc(minHeap, sizeof(trecord));
			(*minHeap).lchild = tmp;
			(*minHeap).rchild = tmp2;
			size --;
			break;
		}
	}
}
void Build_Min_Heap(trecord* S){
	
	int i=(size-1)/2;

	while(i>=0)
	{
		Min_heapify(S,i);
		i = i--;
	}
}
void Min_heapify(trecord* S,int i){
	int left_child = 2*i+1;
	int right_child = 2*i+2;
	int min = 0;

	if(left_child<size && (S)[left_child].freq < (S)[i].freq)
	{
		min = left_child;
	}
	else
	{
		min = i;
	}
	if(right_child<size && (S)[right_child].freq < (S)[min].freq)
	{
		min = right_child;
	}
	if(min != i){
		trecord tmp;
		tmp.alphabet= (S)[min].alphabet;
		tmp.code= (S)[min].code;
		tmp.freq= (S)[min].freq;
		tmp.lchild = (S)[min].lchild;
		tmp.rchild = (S)[min].rchild;

		(S)[min].alphabet= (S)[i].alphabet;
		(S)[min].code= (S)[i].code;
		(S)[min].freq= (S)[i].freq;
		(S)[min].lchild = (S)[i].lchild;
		(S)[min].rchild = (S)[i].rchild;

		(S)[i].alphabet= tmp.alphabet;
		(S)[i].code= tmp.code;
		(S)[i].freq= tmp.freq;
		(S)[i].lchild = tmp.lchild;
		(S)[i].rchild = tmp.rchild;
		Min_heapify(S,min);
	}
}
trecord* extract_max(trecord* S){
	trecord* result = (trecord*)malloc(sizeof(trecord));
	int i=0;
	size -=1;

	(*result).alphabet= (S)[0].alphabet;
	(*result).code = (S)[0].code;
	(*result).freq= (S)[0].freq;
	(*result).lchild= (S)[0].lchild;
	(*result).rchild= (S)[0].rchild;

	(S)[0].alphabet=  (S)[size].alphabet;
	(S)[0].code =  (S)[size].code;
	(S)[0].freq=  (S)[size].freq;
	(S)[0].lchild=  (S)[size].lchild;
	(S)[0].rchild=  (S)[size].rchild;
	S =  (trecord*)realloc(S, sizeof(trecord)*(size));
	
	Build_Min_Heap(S);
	
	return result;
}
void insert(trecord* S, trecord x){
	S =  (trecord*)realloc(S, sizeof(trecord)*(size+1));
	(S)[size].alphabet= x.alphabet;
	(S)[size].code= x.code;
	(S)[size].freq= x.freq;
	(S)[size].lchild= x.lchild;
	(S)[size].rchild= x.rchild;
	size++;
	Build_Min_Heap(S);
}
void MAKE_HUFFMAN_CODE(trecord* root, int* arr, int top)
{
   int i;
   if (root->lchild)
   {
      arr[top] = 0;
      MAKE_HUFFMAN_CODE(root->lchild, arr, top+1);
   }
   if (root->rchild)
   {
      arr[top] = 1;
      MAKE_HUFFMAN_CODE(root->rchild, arr, top+1);
   }

   if (root->rchild == NULL &&root->lchild == NULL)
   {
	  if(root->alphabet == ' '){
		  table[0].data = root->alphabet;
		  for (i = 0; i < top; i++){
			  table[0].huffmancode[i] = arr[i]+48;
		  }
		  table[0].huffmancode[i] = '\0';
	  }
	  else{
		  table[root->alphabet-96].data = root->alphabet;

		  for (i = 0; i < top; i++){
			  table[root->alphabet-96].huffmancode[i] = arr[i]+48;
		  }
		  table[root->alphabet-96].huffmancode[i] = '\0';

	  }
   }
}