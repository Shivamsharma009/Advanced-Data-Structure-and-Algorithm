#include<stdio.h>
#include<stdlib.h>
#include<conio.h>

#include<limits.h>





// Tournament Tree node



struct node

{

	int idx;



	struct node *left, *right;

};



typedef struct node Node;







struct list

{

	Node *n;



	struct list *next;

};



typedef struct list List;





List* push( List *li, Node *n )

{

	List *newNode, *current;



	newNode = (List*)malloc(sizeof(List));



	newNode->n = n;



	if( li==NULL )

	{

		newNode->next = NULL;



		li = newNode;

	}

	else

	{

		current = li;



		while( current ->next != NULL )



			current = current ->next;



		current ->next = newNode;



		newNode->next = NULL;

	}



	return( li );

}





List* pop( List *li )

{

	List *current;



	if( li != NULL )

	{

		current = li;



		li = li->next;



		free( current );

	}

	return( li );

}





int size( List *li)

{

	List *current;



	int count = 0;



	current = li;



	while( current != NULL )

	{

		current=current->next;



		count = count + 1;

	}



	return( count );

}







// Utility function to create a tournament tree node



Node *createNode(int idx)

{

	Node *t = (Node*)malloc(sizeof(Node));



	t->left = t->right = NULL;



	t->idx = idx;



	return t;

}



// This function traverses tree across height to find second smallest element in tournament tree.

// Note that root is smallest element of tournament tree.



void traverseHeight(Node *root, int arr[], int *res)

{



	// Base case



	if (root == NULL || (root->left == NULL && root->right == NULL))



		return;



	// If left child is smaller than current result, update result and recur for left subarray.



	if (*res > arr[root->left->idx] && root->left->idx != root->idx)

	{

			*res = arr[root->left->idx];



		traverseHeight(root->right, arr, res);

	}



	// If right child is smaller than current result, update result and recur for left subarray.



	else if (*res > arr[root->right->idx] && root->right->idx != root->idx)

	{

			*res = arr[root->right->idx];



		traverseHeight(root->left, arr, res);

	}

}



// Prints minimum and second minimum in arr[0..n-1]



void findSecondMin(int arr[], int n)

{

	// Create a list to store nodes of current level



	int i, res;



	int lsize;



	List  *li = NULL;



	Node *root = NULL;



	Node *f1 = NULL, *f2 = NULL, *t1 = NULL, *t2=NULL;







	for (i = 0; i < n; i += 2)

	{



		t1 = createNode(i);



		t2 = NULL;



		if (i + 1 < n)

		{

				// Make a node for next element



			t2 = createNode(i + 1);



				// Make smaller of two as root



			root = (arr[i] < arr[i + 1]) ? createNode(i) : createNode(i + 1);



				// Make two nodes as children of smaller



			root->left = t1;



			root->right = t2;



				// Add root



			li = push( li, root);

		}

		else



			li = push( li, t1);

	}



	lsize = size( li );



	// Construct the complete tournament tree from above prepared list of winners in first round.



	while (lsize != 1)

	{

		// Find index of last pair



		int last = (lsize & 1)? (lsize - 2) : (lsize - 1);



			// Process current list items in pair



		for (i = 0; i < last; i += 2)

		{

				// Extract two nodes from list, make a new node for winner of two



			f1 = li->n;



			li = pop( li );



			f2 = li->n;



			li = pop( li );



			root = (arr[f1->idx] < arr[f2->idx])? createNode(f1->idx) : createNode(f2->idx);



				// Make winner as parent of two



			root->left = f1;



			root->right = f2;



				// Add winner to list of next level



			li = push( li, root);

		}



		if (lsize & 1)

		{

			push( li, li->n );



			pop( li );

		}

		lsize = size( li );

	}



	// Traverse tree from root to find second minimum



	// Note that minimum is already known and root of tournament tree.



	res = INT_MAX;



	traverseHeight(root, arr, &res);



	printf( "\n\n  Minimum : %d \n\n  Second Minimum : %d ", arr[root->idx] , res );

}



// Driver code



int main()

{

	int arr[] = { 1,2,3,4,5,6};



	int n = sizeof(arr)/sizeof(arr[0]);



//	clrscr();



 int x	= findSecondMin(arr, n);
 cout<<Winner of this Tree is<< x;


   return 0;
	getch();

}
