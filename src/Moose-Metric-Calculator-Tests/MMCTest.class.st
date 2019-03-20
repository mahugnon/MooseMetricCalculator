Class {
	#name : #MMCTest,
	#superclass : #TestCase,
	#instVars : [
		'rootCurrent',
		'testModel'
	],
	#category : #'Moose-Metric-Calculator-Tests'
}

{ #category : #running }
MMCTest >> createFamix: aClass named: aString owner: ownerBlock [
	| fmx |
	fmx := aClass new
		name: aString;
		isStub: false;
		mooseModel: testModel;
		yourself.
	ownerBlock value: fmx.
	^ fmx
]

{ #category : #running }
MMCTest >> createTestingModel [
	| pckg clazz mth |
	testModel := MooseModel new
		name: ' testmodel';
		yourself.
	pckg := self
		createFamix: FAMIXPackage
		named: 'TestMokePackage'
		owner: [ :x |  ].
	clazz := self
		createFamix: FAMIXClass
		named: 'TestClass1'
		owner: [ :c | c parentPackage: pckg ].
	clazz hierarchyNestingLevel: 2.
	#('TestClass2' 'TestClass3' 'ModelClass4' 'TestClass4' 'TestClass6' 'TestClass7' 'TestClass8' 'TestClass9')
		do: [ :each | 
			| sub |
			sub := self
				createFamix: FAMIXClass
				named: each
				owner: [ :c | c parentPackage: pckg ].
			self inherit: sub from: clazz ].
	mth := self
		createFamix: FAMIXMethod
		named: 'testMthd1_1'
		owner: [ :m | m parentType: clazz ].
	clazz := testModel entityNamed: 'TestClass3'.
	mth := self
		createFamix: FAMIXMethod
		named: 'testMthd3_1'
		owner: [ :m | m parentType: clazz ].
	MooseModel root add: testModel
]

{ #category : #running }
MMCTest >> inherit: sub from: supr [
	| inh  |
	inh := FAMIXInheritance new
		mooseModel: testModel ;
		yourself.
	sub addSuperInheritance: inh.
	supr addSubInheritance: inh
]

{ #category : #running }
MMCTest >> setUp [
	super setUp.
	rootCurrent := MooseModel root.
	MooseModel resetRoot.
	self createTestingModel.
	
]

{ #category : #running }
MMCTest >> tearDown [
	MooseModel resetRoot.
	MooseModel root addAll: rootCurrent.
	super tearDown
]

{ #category : #tests }
MMCTest >> testBuildQueryString [
| query code|
query:=MMCAbstractQuery new. 
query famixClass: testModel allModelClasses second ;
method: 'query';
direction: #in;
association: FAMIXAccess .
code:= query searchAssociations .
self assert: query buildQueryString equals: ''.
]
