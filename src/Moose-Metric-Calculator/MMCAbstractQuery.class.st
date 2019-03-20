Class {
	#name : #MMCAbstractQuery,
	#superclass : #Object,
	#instVars : [
		'association',
		'method',
		'direction',
		'famixClass'
	],
	#category : #'Moose-Metric-Calculator-Query'
}

{ #category : #accessing }
MMCAbstractQuery class >> displayName [
	^ self subclassResponsibility
]

{ #category : #accessing }
MMCAbstractQuery >> association [
	^ association
]

{ #category : #accessing }
MMCAbstractQuery >> association: anAssociation [
	association := anAssociation
]

{ #category : #accessing }
MMCAbstractQuery >> direction [
	^ direction
]

{ #category : #accessing }
MMCAbstractQuery >> direction: aSymbol [
	direction := aSymbol
]

{ #category : #accessing }
MMCAbstractQuery >> famixClass [
	^ famixClass
]

{ #category : #accessing }
MMCAbstractQuery >> famixClass: aFAMIXClass [
	famixClass := aFAMIXClass
]

{ #category : #accessing }
MMCAbstractQuery >> method [
	^ method,':with:'
]

{ #category : #accessing }
MMCAbstractQuery >> method: aString [ 
	method:= aString
	
]

{ #category : #accessing }
MMCAbstractQuery >> seachAssociationWithAssociationClass [
	^ self famixClass
		perform: self method
		with: self direction
		with: self association
]

{ #category : #accessing }
MMCAbstractQuery >> searchAssociationWithoutAssociationClass [
	^ self famixClass perform: self method with: self direction
]

{ #category : #accessing }
MMCAbstractQuery >> searchAssociations [
	^ self subclassResponsibility
]
