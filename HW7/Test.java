import LivingThing.Ant;
import LivingThing.Fish;

public class Test {

	public static void main(String args[] )	{
		Storage livingThingStorage = new SortedStorageSetWithNulls();
		Fish f1 = new Fish("shark", 10, 10); 
		Fish f2 = new Fish("dolphin", 5, 5); 
		Fish f3 = new Fish("whale", 50, 50); 
		livingThingStorage.add(null);
		livingThingStorage.add(f1);
		livingThingStorage.add(f2);
		livingThingStorage.add(f3);
		System.out.println(livingThingStorage);
		livingThingStorage.delete(f3);
		System.out.println(livingThingStorage);
		livingThingStorage.delete(f2);
		livingThingStorage.delete(f1);
		Ant a1 = new Ant("black ant", 10, 10); 
		Ant a2 = new Ant("red ant", 5, 5); 
		livingThingStorage.add(a1);
		livingThingStorage.add(a2);
		System.out.println(livingThingStorage);
	}
}
