Class {
	#name : #MMCQueryWith,
	#superclass : #MMCAbstractQuery,
	#category : #'Moose-Metric-Calculator-Query'
}

{ #category : #accessing }
MMCQueryWith class >> displayName [
	^ 'query:with:'
]

{ #category : #accessing }
MMCQueryWith >> searchAssociations [
	^ self seachAssociationWithAssociationClass
]
